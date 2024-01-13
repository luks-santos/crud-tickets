package com.lucas.chamados.backendchamadosprojetoufn.jwt.security.controller;

import com.lucas.chamados.backendchamadosprojetoufn.jwt.security.dtos.AuthenticationDTO;
import com.lucas.chamados.backendchamadosprojetoufn.jwt.security.dtos.LoginResponseDTO;
import com.lucas.chamados.backendchamadosprojetoufn.jwt.security.dtos.RegisterDTO;
import com.lucas.chamados.backendchamadosprojetoufn.entities.User;
import com.lucas.chamados.backendchamadosprojetoufn.jwt.security.service.AuthenticationService;
import com.lucas.chamados.backendchamadosprojetoufn.jwt.security.service.TokenService;
import com.lucas.chamados.backendchamadosprojetoufn.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private UserService userService;
    private AuthenticationService service;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {

        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(new LoginResponseDTO(token, user.getRole().toString()));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {

        if (userService.findByLogin(data.login()) != null) {
            return ResponseEntity.unprocessableEntity().body("Login already exists");
        }
        service.register(data);
        return ResponseEntity.created(null).build();
    }
}
