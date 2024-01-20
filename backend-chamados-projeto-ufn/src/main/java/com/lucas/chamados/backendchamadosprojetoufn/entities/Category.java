package com.lucas.chamados.backendchamadosprojetoufn.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "name is mandatory")
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String name;

    /* Posso remover essa lista não é utilizada
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "category")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Topic> topics = new ArrayList<>();
    */
}
