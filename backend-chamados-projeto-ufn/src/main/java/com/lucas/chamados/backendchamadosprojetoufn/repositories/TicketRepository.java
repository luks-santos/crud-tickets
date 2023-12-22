package com.lucas.chamados.backendchamadosprojetoufn.repositories;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {


}
