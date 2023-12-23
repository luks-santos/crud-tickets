package com.lucas.chamados.backendchamadosprojetoufn.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String email;

    private String cellphone;

    @OneToOne(mappedBy = "person")
    private User user;
}
