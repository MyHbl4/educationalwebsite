package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.dto.mapper.TopicMapper;
import com.moon.senla.educational_website.model.dto.topic.TopicDto;
import com.moon.senla.educational_website.model.dto.topic.TopicNewDto;
import com.moon.senla.educational_website.service.TopicService;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/topics")
@Slf4j
@Api(tags = "Topics")
public class TopicController {

    private final TopicService topicService;
    private final TopicMapper topicMapper;

    public TopicController(TopicService topicService,
        TopicMapper topicMapper) {
        this.topicService = topicService;
        this.topicMapper = topicMapper;
    }

    @GetMapping()
    public Page<TopicDto> findAll(@RequestParam int page) {
        PageRequest pageable = PageRequest.of(page, 5, Direction.ASC, "name");
        log.info("findAll - find all topics");
        return topicService.findAll(pageable)
            .map(topicMapper::topicToTopicDto);
    }

    @GetMapping(path = "/{id}")
    public TopicDto findById(@PathVariable(name = "id") long id) {
        log.info("findById - find topic by id: {}", id);
        Topic topic = topicService.findById(id);
        return topicMapper.topicToTopicDto(topic);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public TopicDto save(@Valid @RequestBody TopicNewDto topic) {
        log.info("save - save topic: {}", topic.getName());
        Topic newTopic = topicService.save(topicMapper.topicNewDtoToTopic(topic));
        return topicMapper.topicToTopicDto(newTopic);
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public TopicDto update(@Valid @RequestBody TopicDto topicToUpdate) {
        log.info("update - update topic by id: {}", topicToUpdate.getId());
        Topic topic = topicService.update(topicMapper.topicDtoToTopic(topicToUpdate));
        return topicMapper.topicToTopicDto(topic);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete - delete topic by id: {}", id);
        topicService.deleteById(id);
    }
}