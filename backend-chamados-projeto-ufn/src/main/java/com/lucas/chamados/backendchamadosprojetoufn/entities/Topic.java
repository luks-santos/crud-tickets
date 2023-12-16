package com.lucas.chamados.backendchamadosprojetoufn.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Topic {

    public Topic(UUID id, String name, Category category) {
        this.name = name;
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToOne
    @JsonIgnoreProperties("topics")
    private Category category;

    @OneToMany(mappedBy = "topic")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Ticket> tickets = new ArrayList<>();
}
