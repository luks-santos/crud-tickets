package com.lucas.chamados.backendchamadosprojetoufn.enuns.converters;

import com.lucas.chamados.backendchamadosprojetoufn.enuns.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String value) {
        return Stream.of(Status.values())
                .filter(s -> s.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
