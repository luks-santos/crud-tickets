package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.dto.TicketDTO;
import com.lucas.chamados.backendchamadosprojetoufn.dto.mapper.TicketMapper;
import com.lucas.chamados.backendchamadosprojetoufn.dto.TicketListDTO;
import com.lucas.chamados.backendchamadosprojetoufn.entities.Ticket;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.Status;
import com.lucas.chamados.backendchamadosprojetoufn.exception.RecordNotFoundException;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.TicketRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TicketService {

    private TicketRepository repository;
    private TicketMapper mapper;


    public List<TicketListDTO> findAllWithStatusFilter(String status) {
        List<Ticket> filteredTickets;

        if (status != null && !status.isBlank() && !status.equals("Todos")) {
            filteredTickets = repository.findByStatus(mapper.convertStatusValue(status));
        } else {
            filteredTickets = repository.findAll();
        }

        return filteredTickets.stream()
                .map(mapper::toDTO)
                .sorted(Comparator.comparing(TicketListDTO::createdAt).reversed())
                .toList();
    }

    public List<TicketListDTO> findByUserLoginWithStatusFilter(String status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        List<Ticket> filteredTickets;

        if (status != null && !status.isBlank() && !status.equals("Todos")) {
            filteredTickets = repository.findByUser_LoginAndStatus(login, mapper.convertStatusValue(status));
        } else {
            filteredTickets = repository.findByUser_Login(login);
        }

        return filteredTickets.stream()
                .map(mapper::toDTO)
                .sorted(Comparator.comparing(TicketListDTO::createdAt).reversed())
                .toList();
    }

    public TicketListDTO findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public TicketListDTO save(TicketDTO ticketDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ticketDTO.setUsername(username);
        return mapper.toDTO(repository.save(mapper.toEntity(ticketDTO)));
    }

    public TicketListDTO update(UUID id, TicketDTO ticketDTO) {
        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));

        if (ticket.getStatus() != Status.CANCELADO && ticket.getStatus() != Status.CONCLUIDO) {
            ticket.setStatus(mapper.convertStatusValue(ticketDTO.getStatus()));
        }

        return mapper.toDTO(repository.save(ticket));
    }

    public void delete(UUID id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id))
        );
    }
}
