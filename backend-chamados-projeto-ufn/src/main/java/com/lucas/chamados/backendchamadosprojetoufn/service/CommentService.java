package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Comment;
import com.lucas.chamados.backendchamadosprojetoufn.exception.RecordNotFoundException;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.CommentRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CommentService {

    private CommentRepository repository;

    public List<Comment> findAll() {
        return repository.findAll();
    }

    public Comment findById(UUID id) {

        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));

    }

    public Comment save(@Valid @NotNull Comment comment) {
        return repository.save(comment);
    }

    public Comment update(UUID id, @Valid @NotNull Comment comment) {
            return repository.findById(id)
                    .map(record -> {
                        record.setName(comment.getName());
                        return repository.save(record);
                    })
                    .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(UUID id) {
        repository.delete(
                repository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(id))
        );
    }
}
