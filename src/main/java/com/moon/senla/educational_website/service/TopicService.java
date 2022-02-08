package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.dto.topic.TopicDto;
import com.moon.senla.educational_website.model.dto.topic.TopicNewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicService {

    Topic save(TopicNewDto topic);

    Topic findById(long id);

    Page<Topic> findAll(Pageable pageable);

    void deleteById(long id);

    Topic update(TopicDto topic);
}

