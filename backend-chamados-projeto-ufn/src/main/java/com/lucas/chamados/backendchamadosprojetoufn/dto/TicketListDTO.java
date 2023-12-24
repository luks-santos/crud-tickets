package com.lucas.chamados.backendchamadosprojetoufn.dto;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Comment;
import com.lucas.chamados.backendchamadosprojetoufn.entities.Topic;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketListDTO(
        UUID id,
        String status,
        String priority,
        LocalDateTime createdAt,
        LocalDateTime closedAt,
        String username,
        String personName,
        Comment comment,
        Topic topic
) { }
