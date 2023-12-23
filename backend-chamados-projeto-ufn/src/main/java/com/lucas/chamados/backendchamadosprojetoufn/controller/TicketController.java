package com.lucas.chamados.backendchamadosprojetoufn.controller;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Ticket;
import com.lucas.chamados.backendchamadosprojetoufn.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService service;

    @GetMapping
    public List<Ticket> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Ticket findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Ticket save(Ticket ticket) {
        return service.save(ticket);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

}
