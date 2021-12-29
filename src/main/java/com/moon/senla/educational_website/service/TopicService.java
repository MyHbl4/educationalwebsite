package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Topic;
import java.util.List;

public interface TopicService {

    Topic save(Topic topic);

    Topic findById(long id);

    List<Topic> findAll();

    void deleteById(long id);
}

