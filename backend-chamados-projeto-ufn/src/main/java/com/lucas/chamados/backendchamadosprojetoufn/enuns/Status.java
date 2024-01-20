package com.lucas.chamados.backendchamadosprojetoufn.enuns;

import lombok.Getter;


@Getter
public enum Status {

    PENDENTE("Pendente"),
    EM_ANDAMENTO("Em andamento"),
    CONCLUIDO("Concluído"),
    CANCELADO("Cancelado");

    private final String value;

    Status (String value) {
        this.value = value;
    }
}
