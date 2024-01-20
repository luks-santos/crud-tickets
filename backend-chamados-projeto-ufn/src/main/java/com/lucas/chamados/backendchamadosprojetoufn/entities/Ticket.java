package com.lucas.chamados.backendchamadosprojetoufn.entities;

import com.lucas.chamados.backendchamadosprojetoufn.enuns.Priority;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.Status;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.converters.PriorityConverter;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(max = 300, message = "Ticket description size must be between 0 and 300")
    @Column(length = 300)
    private String description;

    @NotNull(message = "Ticket status is mandatory")
    @Convert(converter = StatusConverter.class)
    private Status status;

    @NotNull(message = "Ticket priority is mandatory")
    @Convert(converter = PriorityConverter.class)
    private Priority priority;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime closedAt = null;

    @NotNull(message = "Ticket comment is mandatory")
    @ManyToOne(optional = false)
    private Comment comment;

    @NotNull(message = "Ticket topic is mandatory")
    @ManyToOne(optional = false)
    private Topic topic;

    @NotNull(message = "Ticket user is mandatory")
    @ManyToOne(optional = false)
    private User user;
}
