package com.lucas.chamados.backendchamadosprojetoufn.dto.mapper;

import com.lucas.chamados.backendchamadosprojetoufn.dto.TicketListDTO;
import com.lucas.chamados.backendchamadosprojetoufn.entities.Ticket;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.Priority;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TicketListMapper {

    public TicketListDTO toDTO(Ticket ticket) {
        return new TicketListDTO(
                ticket.getId(),
                ticket.getStatus().getValue(),
                ticket.getPriority().getValue(),
                ticket.getCreatedAt(),
                ticket.getClosedAt(),
                ticket.getUser().getLogin(),
                ticket.getUser().getPerson().getName(),
                ticket.getComment(),
                ticket.getTopic()
        );
    }

    public Status convertStatusValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "Aberto" -> Status.ABERTO;
            case "Em andamento" -> Status.EM_ANDAMENTO;
            case "Fechado" -> Status.FECHADO;
            default -> throw new IllegalArgumentException("Status inválido: " + value);
        };
    }
    public Priority convertPriorityValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "Baixa" -> Priority.BAIXA;
            case "Média" -> Priority.MEDIA;
            case "Alta" -> Priority.ALTA;
            default -> throw new IllegalArgumentException("Prioridade inválida: " + value);
        };
    }
}
