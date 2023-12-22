package com.lucas.chamados.backendchamadosprojetoufn.controller;


import com.lucas.chamados.backendchamadosprojetoufn.dto.TopicDTO;
import com.lucas.chamados.backendchamadosprojetoufn.entities.Topic;
import com.lucas.chamados.backendchamadosprojetoufn.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public List<Topic> findAll() {
        return this.topicService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Topic findById(@PathVariable UUID id) {
        return this.topicService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TopicDTO save(@RequestBody TopicDTO topic) {
        return this.topicService.save(topic);
    }

    @PutMapping(value = "/{id}")
    public TopicDTO update(@PathVariable UUID id, @RequestBody TopicDTO topic) {
        return this.topicService.update(id, topic);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.topicService.delete(id);
    }
}
