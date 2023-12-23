package com.lucas.chamados.backendchamadosprojetoufn.jwt.security.dtos;


public record LoginResponseDTO(
        String token,
        String role) { }
