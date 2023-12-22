package com.lucas.chamados.backendchamadosprojetoufn.controller;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Ticket;
import com.lucas.chamados.backendchamadosprojetoufn.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> findAll() {
        return ticketService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Ticket findById(@PathVariable UUID id) {
        return ticketService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Ticket save(Ticket ticket) {
        return ticketService.save(ticket);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        ticketService.delete(id);
    }

}
