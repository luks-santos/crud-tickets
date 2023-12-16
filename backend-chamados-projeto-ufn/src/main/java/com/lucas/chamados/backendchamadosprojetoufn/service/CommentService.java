package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Comment;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAll() {
        return this.commentRepository.findAll();
    }

    public Comment findById(UUID id) {
        return this.commentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
    public Comment save(Comment comment) {
        return this.commentRepository.save(comment);
    }

    public Comment update(UUID id, Comment comment) {
        return commentRepository.findById(id)
                .map(entity -> {
                    updateComment(entity, comment);
                    return this.commentRepository.save(entity);
                }).orElseThrow(EntityNotFoundException::new);
    }

    public void delete(UUID id) {
        this.commentRepository.delete(this.commentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
    private void updateComment(Comment entity, Comment comment) {
        entity.setName(comment.getName());
    }
}
