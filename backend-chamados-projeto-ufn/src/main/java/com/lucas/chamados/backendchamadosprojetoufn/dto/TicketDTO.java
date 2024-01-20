package com.lucas.chamados.backendchamadosprojetoufn.dto;

import com.lucas.chamados.backendchamadosprojetoufn.enuns.Priority;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.Status;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.validations.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TicketDTO {
    private UUID id;

    @Size(max = 300, message = "Ticket description size must be between 0 and 300")
    private String description;

    @ValueOfEnum(enumClass = Status.class)
    @NotBlank(message = "Ticket status is mandatory")
    private String status;

    @ValueOfEnum(enumClass = Priority.class)
    @NotBlank(message = "Ticket priority is mandatory")
    private String priority;

    private LocalDateTime createdAt;
    private LocalDateTime closedAt;

    @NotNull(message = "Ticket commentId is mandatory")
    private UUID commentId;

    @NotNull(message = "Ticket topicId is mandatory")
    private UUID topicId;

    @NotBlank(message = "Ticket user is mandatory")
    private String username;
}


