package com.lucas.chamados.backendchamadosprojetoufn.repositories;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {

    List<Topic> findTopicsByCategoryId(UUID categoryId);
}
