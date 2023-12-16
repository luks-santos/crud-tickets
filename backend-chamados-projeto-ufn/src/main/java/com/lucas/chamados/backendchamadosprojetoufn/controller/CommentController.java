package com.lucas.chamados.backendchamadosprojetoufn.controller;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Category;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Comment;
import com.lucas.chamados.backendchamadosprojetoufn.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> findAll() {
        return this.commentService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Comment findById(@PathVariable UUID id) {
        return this.commentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Comment save(@RequestBody Comment comment) {
        return this.commentService.save(comment);
    }

    @PutMapping(value = "/{id}")
    public Comment update(@PathVariable UUID id, @RequestBody Comment comment) {
        return this.commentService.update(id, comment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.commentService.delete(id);
    }
}
