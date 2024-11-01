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

    /**
     * Valida o token enviado para a autenticação do usuário na requisição enviada.
     * @author Clayton Charles
     * @version 0.1.0
     */
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
            if (user != null) {
                UsernamePasswordAuthenticationToken authUser = new UsernamePasswordAuthenticationToken(
                    user, 
                    null,
                    user.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authUser);
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Resgata o token JWT, enviado nos headers da requisição.
     * @param request {@link HttpServletRequest}
     * @return {@link String}
     * @author Clayton Charles
     * @version 0.1.0
     */
    private String recoverTokenJWT(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) return null;
        return authorization.replace("Bearer ", "");
    }    
}
