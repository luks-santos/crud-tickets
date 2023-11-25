package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Category;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    public Category findById(UUID id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    public Category update(UUID id, Category category) {
        return categoryRepository.findById(id)
                .map(entity -> {
                    updateCategory(entity, category);
                    return this.categoryRepository.save(entity);
                }).orElseThrow(EntityNotFoundException::new);
    }

    public void delete(UUID id) {
        this.categoryRepository.delete(this.categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
    private void updateCategory(Category entity, Category category) {
        entity.setName(category.getName());
    }
}
