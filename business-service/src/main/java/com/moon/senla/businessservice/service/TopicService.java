package com.moon.senla.businessservice.service;

import com.moon.senla.businessservice.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicService {

    Topic save(Topic topic);

    Topic findById(long id);

    Page<Topic> findAll(Pageable pageable);

    void deleteById(long id);

    Topic update(Topic topic);
}

