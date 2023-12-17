package com.lucas.chamados.backendchamadosprojetoufn.service;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Comment;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAll() {
        try {
            return this.commentRepository.findAll();
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao buscar todos os comentários", e);
            throw e;
        }
    }

    public Comment findById(UUID id) {
        try {
            return this.commentRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Comentário não encontrado com o ID: " + id));
        } catch (EntityNotFoundException e) {
            logger.warn("Comentário não encontrado com o ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao buscar o comentário com o ID: {}", id, e);
            throw e;
        }
    }

    public Comment save(Comment comment) {
        try {
            return this.commentRepository.save(comment);
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao salvar o comentário", e);
            throw e;
        }
    }

    public Comment update(UUID id, Comment comment) {
        try {
            return commentRepository.findById(id)
                    .map(entity -> {
                        updateComment(entity, comment);
                        return this.commentRepository.save(entity);
                    }).orElseThrow(() -> new EntityNotFoundException("Comentário não encontrado com o ID: " + id));
        } catch (EntityNotFoundException e) {
            logger.warn("Comentário não encontrado com o ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao atualizar o comentário com o ID: {}", id, e);
            throw e;
        }
    }

    public void delete(UUID id) {
        try {
            this.commentRepository.delete(this.commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comentário não encontrado com o ID: " + id)));
        } catch (EntityNotFoundException e) {
            logger.warn("Comentário não encontrado com o ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao excluir o comentário com o ID: {}", id, e);
            throw e;
        }
    }

    private void updateComment(Comment entity, Comment comment) {
        entity.setName(comment.getName());
    }
}
