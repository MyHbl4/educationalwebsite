package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Topic;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicService {

    Topic save(Topic topic);

    Topic findById(long id);

    Page<Topic> findAll(Pageable pageable);

    void deleteById(long id);
}

