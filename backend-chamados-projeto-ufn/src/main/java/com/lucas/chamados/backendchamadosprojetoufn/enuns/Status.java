package com.lucas.chamados.backendchamadosprojetoufn.enuns;

import lombok.Getter;

@Getter
public enum Status {

    ABERTO("Aberto"),
    EM_ANDAMENTO("Em andamento"),
    FECHADO("Fechado");

    private final String status;
    Status (String status) {
        this.status = status;
    }

}
