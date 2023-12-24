package com.lucas.chamados.backendchamadosprojetoufn.controller;

import com.lucas.chamados.backendchamadosprojetoufn.dto.TicketDTO;
import com.lucas.chamados.backendchamadosprojetoufn.dto.TicketListDTO;
import com.lucas.chamados.backendchamadosprojetoufn.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService service;

    @GetMapping
    public List<TicketListDTO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public TicketListDTO findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TicketListDTO save(@RequestBody TicketDTO ticketDTO) {
        return service.save(ticketDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

}
