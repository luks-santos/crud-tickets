package com.lucas.chamados.backendchamadosprojetoufn.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    //@OneToMany(mappedBy = "category")
    //private List<Topic> topics = new ArrayList<>();
}
