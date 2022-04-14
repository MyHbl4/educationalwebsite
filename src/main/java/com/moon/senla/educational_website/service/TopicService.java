package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicService {

    Topic save(Topic topic);

    Topic findById(String id);

    Page<Topic> findAll(Pageable pageable);

    void deleteById(String id);

    Topic update(Topic topic);
}

