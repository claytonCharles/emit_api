package com.clayton.emit_api.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clayton.emit_api.auth.data.dtos.ResponseAuthDTO;
import com.clayton.emit_api.auth.data.dtos.UserAuthDTO;
import com.clayton.emit_api.auth.domain.entities.UserEntity;
import com.clayton.emit_api.core.data.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;
    
    @PostMapping("login")
    public ResponseEntity<ResponseAuthDTO> login(@RequestBody @Valid UserAuthDTO authDTO) throws Exception {
        var userPass = new UsernamePasswordAuthenticationToken(authDTO.mail(), authDTO.password());
        var auth = this.authManager.authenticate(userPass);
        String token = tokenService.generateTokenJWT((UserEntity) auth.getPrincipal());
        return ResponseEntity.ok(new ResponseAuthDTO(token));
    }
}
