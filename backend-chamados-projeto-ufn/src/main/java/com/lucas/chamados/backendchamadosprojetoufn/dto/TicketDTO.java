package com.lucas.chamados.backendchamadosprojetoufn.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketDTO(
        UUID id,
        String status,
        String priority,
        LocalDateTime createdAt,
        LocalDateTime closedAt,
        UUID commentId,
        UUID topicId,
        String username
) { }
