package com.lucas.chamados.backendchamadosprojetoufn.enuns;

public enum Priority {

    BAIXA("Baixa"),
    MEDIA("Média"),
    ALTA("Alta");

    private final String prioridade;

    Priority(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getPrioridade() {
        return prioridade;
    }
}
