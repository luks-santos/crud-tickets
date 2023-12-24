package com.lucas.chamados.backendchamadosprojetoufn.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @NotNull
    @Length(max = 200)
    @Column(nullable = false, length = 200)
    private String name;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("topics")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "topic")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Ticket> tickets = new ArrayList<>();

    public Topic(UUID id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
}
