package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.dto.TopicDTO;
import com.lucas.chamados.backendchamadosprojetoufn.entities.Category;
import com.lucas.chamados.backendchamadosprojetoufn.entities.Topic;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.CategoryRepository;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TopicService {

    private static final Logger logger = LoggerFactory.getLogger(TopicService.class);

    private final TopicRepository topicRepository;
    private final CategoryRepository categoryRepository;

    public TopicService(TopicRepository topicRepository, CategoryRepository categoryRepository) {
        this.topicRepository = topicRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Topic> findAll() {
        try {
            return this.topicRepository.findAll();
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao buscar todos os tópicos", e);
            throw e;
        }
    }

    public Topic findById(UUID id) {
        try {
            return this.topicRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado com o ID: " + id));
        } catch (EntityNotFoundException e) {
            logger.warn("Tópico não encontrado com o ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao buscar o tópico com o ID: {}", id, e);
            throw e;
        }
    }

    public TopicDTO save(TopicDTO topicDTO) {
        try {
            return toDTO(this.topicRepository.save(toEntity(topicDTO)));
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao salvar o tópico", e);
            throw e;
        }
    }

    public TopicDTO update(UUID id, TopicDTO topicDTO) {
        try {
            Topic entity = this.topicRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado com o ID: " + id));

            updateTopic(entity, toEntity(topicDTO));
            return toDTO(this.topicRepository.save(entity));
        } catch (EntityNotFoundException e) {
            logger.warn("Tópico não encontrado com o ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao atualizar o tópico com o ID: {}", id, e);
            throw e;
        }
    }

    public void delete(UUID id) {
        try {
            this.topicRepository.delete(this.topicRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado com o ID: " + id)));
        } catch (EntityNotFoundException e) {
            logger.warn("Tópico não encontrado com o ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao excluir o tópico com o ID: {}", id, e);
            throw e;
        }
    }

    private void updateTopic(Topic entity, Topic topic) {
        entity.setName(topic.getName());
        entity.setCategory(topic.getCategory());
    }

    private Topic toEntity(TopicDTO topicDTO) {
        try {
            Category category = this.categoryRepository.findById(topicDTO.categoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + topicDTO.categoryId()));
            return new Topic(topicDTO.id(), topicDTO.name(), category);
        } catch (EntityNotFoundException e) {
            logger.warn("Categoria não encontrada com o ID: {}", topicDTO.categoryId());
            throw e;
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao converter DTO para entidade", e);
            throw e;
        }
    }

    private TopicDTO toDTO(Topic topic) {
        return new TopicDTO(topic.getId(), topic.getName(), topic.getCategory().getId());
    }
}
