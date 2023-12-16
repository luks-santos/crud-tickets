package com.lucas.chamados.backendchamadosprojetoufn.dto.jwt;

import com.lucas.chamados.backendchamadosprojetoufn.enuns.jwt.UserRole;

public record RegisterDTO (
    String login,
    String password,
    UserRole role) { }
