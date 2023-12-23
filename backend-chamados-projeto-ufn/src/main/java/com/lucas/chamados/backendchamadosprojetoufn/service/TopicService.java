package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.dto.TopicDTO;
import com.lucas.chamados.backendchamadosprojetoufn.dto.mapper.TopicMapper;
import com.lucas.chamados.backendchamadosprojetoufn.entities.Topic;
import com.lucas.chamados.backendchamadosprojetoufn.exception.RecordNotFoundException;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.TopicRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TopicService {

    private final TopicRepository repository;
    private final TopicMapper mapper;

    public List<Topic> findAll() {
        return repository.findAll();
    }

    public Topic findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));

    }

    public TopicDTO save(@Valid @NotNull TopicDTO topicDTO) {
        return mapper.toDTO(this.repository.save(mapper.toEntity(topicDTO)));
    }

    public TopicDTO update(UUID id, @Valid @NotNull TopicDTO topicDTO) {
        return repository.findById(id)
                .map(entity -> {
                    updateTopic(entity, mapper.toEntity(topicDTO));
                    return mapper.toDTO(this.repository.save(entity));
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(UUID id) {
        repository.delete(repository.findById(id)
                    .orElseThrow(() -> new RecordNotFoundException(id))
        );
    }

    private void updateTopic(Topic entity, Topic topic) {
        entity.setName(topic.getName());
        entity.setCategory(topic.getCategory());
    }
}
