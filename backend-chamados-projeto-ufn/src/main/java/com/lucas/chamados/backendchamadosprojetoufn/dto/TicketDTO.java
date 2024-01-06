package com.lucas.chamados.backendchamadosprojetoufn.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TicketDTO {
    private UUID id;
    private String description;
    private String status;
    private String priority;
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    private UUID commentId;
    private UUID topicId;
    private String username;
}


