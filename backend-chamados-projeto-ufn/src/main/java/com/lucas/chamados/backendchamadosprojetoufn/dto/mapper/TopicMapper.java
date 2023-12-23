package com.lucas.chamados.backendchamadosprojetoufn.dto.mapper;

import com.lucas.chamados.backendchamadosprojetoufn.dto.TopicDTO;
import com.lucas.chamados.backendchamadosprojetoufn.entities.Category;
import com.lucas.chamados.backendchamadosprojetoufn.entities.Topic;
import com.lucas.chamados.backendchamadosprojetoufn.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TopicMapper {

    private final CategoryService categoryService;

    public TopicDTO toDTO(Topic topic) {
        return new TopicDTO(topic.getId(), topic.getName(), topic.getCategory().getId());
    }

    public Topic toEntity(TopicDTO topicDTO) {
        Category category = categoryService.findById(topicDTO.categoryId());
        return new Topic(topicDTO.id(), topicDTO.name(), category);
    }
}
