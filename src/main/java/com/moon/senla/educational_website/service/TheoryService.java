package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TheoryService {

    Theory save(Theory theory);

    Theory findById(long id);

    Page<Theory> findAll(Pageable pageable);

    void deleteById(long id);

    List<Theory> findAllTheoriesByTopicId(long id);

    List<Theory> findAllTheoriesByParam(String name, Topic topic, User user);
}

