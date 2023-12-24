package com.lucas.chamados.backendchamadosprojetoufn.jwt.security.service;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Person;
import com.lucas.chamados.backendchamadosprojetoufn.entities.User;
import com.lucas.chamados.backendchamadosprojetoufn.jwt.security.dtos.RegisterDTO;
import com.lucas.chamados.backendchamadosprojetoufn.service.PersonService;
import com.lucas.chamados.backendchamadosprojetoufn.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private final UserService userService;
    private final PersonService personService;

    public User register(RegisterDTO data) {

        Person person = new Person(data.name(), data.cellphone());
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        return userService.save(new User(data.login(), encryptedPassword, data.role(), person));
    }
}
