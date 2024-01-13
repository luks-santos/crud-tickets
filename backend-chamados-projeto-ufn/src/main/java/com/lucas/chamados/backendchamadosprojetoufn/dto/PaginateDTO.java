package com.lucas.chamados.backendchamadosprojetoufn.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Getter
@Setter
public class PaginateDTO {

    private int page = 0;
    private int size = 10;

    public Pageable createPageable() {
       return PageRequest.of(page, size);
    }
}
