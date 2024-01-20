package com.lucas.chamados.backendchamadosprojetoufn.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Topic name is mandatory")
    @Size(max = 200, message = "Topic name size must be between 0 and 200")
    @Column(nullable = false, length = 200)
    private String name;

    @NotNull(message = "Topic category is mandatory")
    @ManyToOne(optional = false)
    private Category category;
}
