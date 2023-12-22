package com.lucas.chamados.backendchamadosprojetoufn.entities;

import com.lucas.chamados.backendchamadosprojetoufn.entities.user.User;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.Priority;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.Status;
import jakarta.persistence.*;
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

    private Status status;

    private Priority priority;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime closedAt = null;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
