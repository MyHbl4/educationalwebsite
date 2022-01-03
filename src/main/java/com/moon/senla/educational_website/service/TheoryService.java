package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
import java.util.List;

public interface TheoryService {

    Theory save(Theory theory);

    Theory findById(long id);

    List<Theory> findAll();

    void deleteById(long id);

    List<Theory> findAllTheoriesByTopicId(long id);

    List<Theory> findAllTheoriesByParam(String name, Topic topic, User user);
}

