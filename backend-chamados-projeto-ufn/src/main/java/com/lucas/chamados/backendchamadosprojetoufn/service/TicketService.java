package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.dto.TicketDTO;
import com.lucas.chamados.backendchamadosprojetoufn.dto.mapper.TicketListMapper;
import com.lucas.chamados.backendchamadosprojetoufn.dto.mapper.TicketMapper;
import com.lucas.chamados.backendchamadosprojetoufn.dto.TicketListDTO;
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
    private final TicketMapper mapper;
    private final TicketListMapper listMapper;

    public List<TicketListDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(listMapper::toDTO)
                .toList();
    }

    public TicketListDTO findById(UUID id) {
        return repository.findById(id)
                .map(listMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public TicketListDTO save(@Valid @NotNull TicketDTO ticketDTO) {
        return listMapper.toDTO(repository.save(mapper.toEntity(ticketDTO)));
    }

    public void delete(UUID id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id))
        );
    }
}
