package com.lucas.chamados.backendchamadosprojetoufn.enuns.converters;

import com.lucas.chamados.backendchamadosprojetoufn.enuns.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        return status.getStatus();
    }

    @Override
    public Status convertToEntityAttribute(String status) {
        return Stream.of(Status.values())
                .filter(s -> s.getStatus().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
