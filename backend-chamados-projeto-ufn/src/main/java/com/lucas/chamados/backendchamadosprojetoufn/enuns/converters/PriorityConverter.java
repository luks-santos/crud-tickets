package com.lucas.chamados.backendchamadosprojetoufn.enuns.converters;

import com.lucas.chamados.backendchamadosprojetoufn.enuns.Priority;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class PriorityConverter implements AttributeConverter<Priority, String> {

    @Override
    public String convertToDatabaseColumn(Priority priority) {
        return priority.getValue();
    }

    @Override
    public Priority convertToEntityAttribute(String value) {
        return Stream.of(Priority.values())
                .filter(p -> p.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
