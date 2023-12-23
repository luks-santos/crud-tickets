package com.lucas.chamados.backendchamadosprojetoufn.jwt.security;

import com.lucas.chamados.backendchamadosprojetoufn.jwt.security.dtos.AuthenticationDTO;
import com.lucas.chamados.backendchamadosprojetoufn.jwt.security.dtos.LoginResponseDTO;
import com.lucas.chamados.backendchamadosprojetoufn.jwt.security.dtos.RegisterDTO;
import com.lucas.chamados.backendchamadosprojetoufn.entities.User;
import com.lucas.chamados.backendchamadosprojetoufn.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;

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
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        userService.save(newUser);

        return ResponseEntity.ok().build();
    }

}
