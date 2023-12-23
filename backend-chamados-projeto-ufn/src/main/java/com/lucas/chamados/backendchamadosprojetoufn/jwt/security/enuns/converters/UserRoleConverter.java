package com.lucas.chamados.backendchamadosprojetoufn.jwt.security.enuns.converters;

import com.lucas.chamados.backendchamadosprojetoufn.jwt.security.enuns.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        if (userRole == null) {
            return null;
        }
        return userRole.getRole();
    }

    @Override
    public UserRole convertToEntityAttribute(String role) {
        return Stream.of(UserRole.values())
                .filter(r -> r.getRole().equals(role))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
