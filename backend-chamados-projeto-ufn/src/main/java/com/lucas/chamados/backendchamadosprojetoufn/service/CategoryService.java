package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Category;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        try {
            return this.categoryRepository.findAll();
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao buscar todas as categorias", e);
            throw e;
        }
    }

    public Category findById(UUID id) {
        try {
            return this.categoryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + id));
        } catch (EntityNotFoundException e) {
            logger.warn("Categoria não encontrada com o ID: {}", id);
            throw e; // Rethrow the exception for the caller to handle
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao buscar a categoria com o ID: {}", id, e);
            throw e;
        }
    }

    public Category save(Category category) {
        try {
            return this.categoryRepository.save(category);
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao salvar a categoria", e);
            throw e;
        }
    }

    public Category update(UUID id, Category category) {
        try {
            return categoryRepository.findById(id)
                    .map(entity -> {
                        updateCategory(entity, category);
                        return this.categoryRepository.save(entity);
                    }).orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + id));
        } catch (EntityNotFoundException e) {
            logger.warn("Categoria não encontrada com o ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao atualizar a categoria com o ID: {}", id, e);
            throw e;
        }
    }

    public void delete(UUID id) {
        try {
            this.categoryRepository.delete(this.categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + id)));
        } catch (EntityNotFoundException e) {
            logger.warn("Categoria não encontrada com o ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao excluir a categoria com o ID: {}", id, e);
            throw e;
        }
    }

    private void updateCategory(Category entity, Category category) {
        entity.setName(category.getName());
    }
}
