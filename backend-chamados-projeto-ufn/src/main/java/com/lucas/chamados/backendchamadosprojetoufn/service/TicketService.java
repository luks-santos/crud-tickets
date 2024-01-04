package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.dto.TicketDTO;
import com.lucas.chamados.backendchamadosprojetoufn.dto.mapper.TicketMapper;
import com.lucas.chamados.backendchamadosprojetoufn.dto.TicketListDTO;
import com.lucas.chamados.backendchamadosprojetoufn.exception.RecordNotFoundException;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.TicketRepository;
import jakarta.validation.Valid;
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

    private final TicketRepository repository;
    private final TicketMapper mapper;

    public List<TicketListDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .sorted(Comparator.comparing(TicketListDTO::createdAt).reversed())
                .toList();
    }

    public TicketListDTO findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public TicketListDTO save(@Valid @NotNull TicketDTO ticketDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ticketDTO.setUsername(username);
        return mapper.toDTO(repository.save(mapper.toEntity(ticketDTO)));
    }

    public void delete(UUID id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id))
        );
    }

    public List<TicketListDTO> findByUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        return repository.findByUser_Login(login)
                .stream()
                .map(mapper::toDTO)
                .sorted(Comparator.comparing(TicketListDTO::createdAt).reversed())
                .toList();
    }
}
