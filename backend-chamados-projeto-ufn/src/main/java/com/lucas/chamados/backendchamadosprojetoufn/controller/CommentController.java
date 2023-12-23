package com.lucas.chamados.backendchamadosprojetoufn.controller;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Comment;
import com.lucas.chamados.backendchamadosprojetoufn.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/comments")
public class CommentController {

    private final CommentService service;

    @GetMapping
    public List<Comment> findAll() {
        return this.service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Comment findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Comment save(@RequestBody Comment comment) {
        return service.save(comment);
    }

    @PutMapping(value = "/{id}")
    public Comment update(@PathVariable UUID id, @RequestBody Comment comment) {
        return service.update(id, comment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
