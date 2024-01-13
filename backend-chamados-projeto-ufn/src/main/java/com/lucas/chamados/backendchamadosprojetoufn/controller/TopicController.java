package com.lucas.chamados.backendchamadosprojetoufn.controller;


import com.lucas.chamados.backendchamadosprojetoufn.dto.TopicDTO;
import com.lucas.chamados.backendchamadosprojetoufn.entities.Topic;
import com.lucas.chamados.backendchamadosprojetoufn.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private  TopicService service;

    @GetMapping
    public List<Topic> findAll() {
        return this.service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Topic findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TopicDTO save(@RequestBody TopicDTO topic) {
        return service.save(topic);
    }

    @PutMapping(value = "/{id}")
    public TopicDTO update(@PathVariable UUID id, @RequestBody TopicDTO topic) {
        return service.update(id, topic);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @GetMapping(value = "/category/{categoryId}")
    public List<Topic> findTopicsByCategoryId(@PathVariable UUID categoryId) {
        return service.findTopicsByCategory(categoryId);
    }
}
