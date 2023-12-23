package com.lucas.chamados.backendchamadosprojetoufn.jwt.security.dtos;

public record AuthenticationDTO(
        String login,
        String password) { }
