package com.lucas.chamados.backendchamadosprojetoufn.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TopicDTO(
        UUID id,

        @NotBlank(message = "Topic name is mandatory")
        String name,

        @NotNull(message = "Topic categoryId is mandatory")
        UUID categoryId
    ) { }
