package com.clayton.emit_api.core.infra.security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.clayton.emit_api.auth.domain.repositories.UserRepository;
import com.clayton.emit_api.core.data.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtSecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain
    ) throws ServletException, IOException {
        String tokenJWT = this.recoverTokenJWT(request);
        if (tokenJWT != null) {
            String userMail = tokenService.validateTokenJWT(tokenJWT);
            UserDetails user = userRepository.findByMail(userMail);
            UsernamePasswordAuthenticationToken authUser = new UsernamePasswordAuthenticationToken(
                user, 
                null,
                user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authUser);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverTokenJWT(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) return null;
        return authorization.replace("Bearer ", "");
    }    
}
