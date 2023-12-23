package com.lucas.chamados.backendchamadosprojetoufn.exception;

import java.io.Serial;
import java.util.UUID;

public class RecordNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(UUID id) {
        super("Registro não encontrado com o id: " + id);
    }
}
