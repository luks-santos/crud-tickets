package com.lucas.chamados.backendchamadosprojetoufn.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Comment name is mandatory")
    @Size(max = 200, message = "Comment name size must be between 0 and 200")
    @Column(nullable = false, length = 200)
    private String name;
}
