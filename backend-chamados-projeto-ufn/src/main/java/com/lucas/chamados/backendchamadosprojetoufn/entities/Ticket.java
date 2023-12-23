package com.lucas.chamados.backendchamadosprojetoufn.entities;

import com.lucas.chamados.backendchamadosprojetoufn.enuns.Priority;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.Status;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.converters.PriorityConverter;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status;

    @NotNull
    @Convert(converter = PriorityConverter.class)
    private Priority priority;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime closedAt = null;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
}
