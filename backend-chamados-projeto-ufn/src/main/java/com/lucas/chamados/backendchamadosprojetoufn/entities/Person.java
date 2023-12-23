package com.lucas.chamados.backendchamadosprojetoufn.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;
@Getter
@Setter
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @NotNull
    @Length(max = 200)
    @Column(nullable = false, length = 200)
    private String name;

    @Length(max = 20)
    @Column(nullable = false, length = 20)
    private String cellphone;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "person")
    private User user;
}
