package com.lucas.chamados.backendchamadosprojetoufn.dto.topic;

import java.util.UUID;

public record TopicDTO(
        UUID id,
        String name,
        UUID categoryId
    ) { }
