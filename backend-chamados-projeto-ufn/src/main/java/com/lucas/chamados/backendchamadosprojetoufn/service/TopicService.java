package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.dto.topic.TopicDTO;
import com.lucas.chamados.backendchamadosprojetoufn.entities.Category;
import com.lucas.chamados.backendchamadosprojetoufn.entities.Topic;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.CategoryRepository;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final CategoryRepository categoryRepository;

    public TopicService(TopicRepository topicRepository, CategoryRepository categoryRepository) {
        this.topicRepository = topicRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Topic> findAll() {
        return this.topicRepository.findAll();
    }

    public Topic findById(UUID id) {
        return this.topicRepository.findById(id).orElse(null);
    }

    public TopicDTO save(TopicDTO topicDTO) {
        return toDTO(this.topicRepository.save(toEntity(topicDTO)));
    }

    public TopicDTO update(UUID id, TopicDTO topicDTO) {
        Topic entity = this.topicRepository.findById(id).orElse(null);

        if (entity != null) {
            updateTopic(entity, toEntity(topicDTO));
            return toDTO(this.topicRepository.save(entity));
        }
        return null;
    }

    public void delete(UUID id) {
        this.topicRepository.delete(this.topicRepository.findById(id).orElse(null));
    }

    private void updateTopic(Topic entity, Topic topic) {
        entity.setName(topic.getName());
        entity.setCategory(topic.getCategory());
    }

    private Topic toEntity(TopicDTO topicDTO) {
        Category category = this.categoryRepository.findById(topicDTO.categoryId()).orElse(null);
        return new Topic(topicDTO.id(), topicDTO.name(), category);
    }

    private TopicDTO toDTO(Topic topic) {
        return new TopicDTO(topic.getId(), topic.getName(), topic.getCategory().getId());
    }
}
