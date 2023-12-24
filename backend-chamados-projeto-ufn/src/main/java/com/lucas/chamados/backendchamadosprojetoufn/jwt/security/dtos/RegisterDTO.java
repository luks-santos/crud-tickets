package com.lucas.chamados.backendchamadosprojetoufn.jwt.security.dtos;

import com.lucas.chamados.backendchamadosprojetoufn.jwt.security.enuns.UserRole;

public record RegisterDTO (
        String login,
        String password,
        UserRole role,
        String name,
        String cellphone) { }
