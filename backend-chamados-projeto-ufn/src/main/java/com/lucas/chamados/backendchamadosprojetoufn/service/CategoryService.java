package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Category;
import com.lucas.chamados.backendchamadosprojetoufn.exception.RecordNotFoundException;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public Category update(UUID id, Category category) {
        return repository.findById(id)
                .map(record -> {
                    record.setName(category.getName());
                    return repository.save(record);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(UUID id) {
        repository.delete(
                repository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(id))
        );
    }
}
