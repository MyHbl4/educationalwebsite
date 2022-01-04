package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.dto.mapper.TopicMapper;
import com.moon.senla.educational_website.model.dto.topic.TopicDto;
import com.moon.senla.educational_website.model.dto.topic.TopicPageDto;
import com.moon.senla.educational_website.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public TopicPageDto findAllPageable(@PageableDefault(sort = {"id"}, size = 3)
        Pageable pageable) {
        log.info("find all topics");
        return topicService.findAllPageable(pageable);
    }

    @GetMapping(path = "/{id}")
    public TopicDto findById(@PathVariable(name = "id") long id) {
        log.info("find topic by id {}", id);
        Topic topic = topicService.findById(id);
        return TopicMapper.INSTANCE.topicToTopicDto(topic);
    }

    @PostMapping()
    public TopicDto save(@RequestBody Topic topic) {
        log.info("save topic {}", topic);
        Topic newTopic = topicService.save(topic);
        return TopicMapper.INSTANCE.topicToTopicDto(newTopic);
    }

    @PutMapping()
    public TopicDto update(@RequestBody Topic topicToUpdate) {
        log.info("update topic {}", topicToUpdate);
        Topic topic = topicService.save(topicToUpdate);
        return TopicMapper.INSTANCE.topicToTopicDto(topic);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete topic by id {}", id);
        topicService.deleteById(id);
    }
}