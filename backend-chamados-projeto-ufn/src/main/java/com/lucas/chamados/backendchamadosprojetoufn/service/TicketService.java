package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Ticket;
import com.lucas.chamados.backendchamadosprojetoufn.exception.RecordNotFoundException;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.TicketRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TicketService {

    private final TicketRepository repository;

    public List<Ticket> findAll() {
        return repository.findAll();
    }

    public Ticket findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Ticket save(@Valid @NotNull Ticket ticket) {
        return repository.save(ticket);
    }

    public void delete(UUID id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id))
        );
    }
}
