package com.lucas.chamados.backendchamadosprojetoufn.repositories;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
