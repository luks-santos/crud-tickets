package com.lucas.chamados.backendchamadosprojetoufn.enuns;

import lombok.Getter;

@Getter
public enum Priority {

    BAIXA("Baixa"),
    MEDIA("Média"),
    ALTA("Alta");

    private final String value;

    Priority(String value) {
        this.value = value;
    }
}
