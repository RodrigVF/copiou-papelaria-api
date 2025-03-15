package com.rodrigvf.copiou_papelaria_api.controller;

import com.rodrigvf.copiou_papelaria_api.config.TokenService;
import com.rodrigvf.copiou_papelaria_api.dto.request.LoginRequest;
import com.rodrigvf.copiou_papelaria_api.dto.response.LoginResponse;
import com.rodrigvf.copiou_papelaria_api.entity.User;
import com.rodrigvf.copiou_papelaria_api.exception.UsernameOrPasswordInvalidException;
import com.rodrigvf.copiou_papelaria_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {

            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            User user = (User) authenticate.getPrincipal();

            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException e) {
            throw new UsernameOrPasswordInvalidException("Usuário ou senha inválido.");
        }
    }

}
