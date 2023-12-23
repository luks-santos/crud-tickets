package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.entities.User;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User findByLogin(String login) {
        return repository.findByLogin(login);
    }
}
