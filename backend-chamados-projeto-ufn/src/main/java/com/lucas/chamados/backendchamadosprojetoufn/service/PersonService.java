package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Person;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PersonService {

    private PersonRepository repository;

    public Person save(Person person) {
        return repository.save(person);
    }
}
