package com.lucas.chamados.backendchamadosprojetoufn.enuns;

import lombok.Getter;

@Getter
public enum Priority {

    BAIXA("Baixa"),
    MEDIA("Média"),
    ALTA("Alta");

    private final String priority;

    Priority(String priority) {
        this.priority = priority;
    }
}
