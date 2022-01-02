package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.service.TopicService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/topics")
@Slf4j
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping()
    public List<Topic> findAll() {
        log.info("find all topics");
        return topicService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Topic findById(@PathVariable(name = "id") long id) {
        log.info("find topic by id {}", id);
        return topicService.findById(id);
    }

    @PostMapping()
    public Topic save(@RequestBody Topic topic) {
        log.info("save topic {}", topic);
        return topicService.save(topic);
    }

    @PutMapping()
    public Topic update(@RequestBody Topic topicToUpdate) {
        log.info("update topic {}", topicToUpdate);
        return topicService.save(topicToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete topic by id {}", id);
        topicService.deleteById(id);
    }
}