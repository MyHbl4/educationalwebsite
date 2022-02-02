package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Theory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TheoryService {

    Theory save(Theory theory);

    Theory findById(long id);

    Page<Theory> findAll(Pageable pageable);

    void deleteById(long id);

    Page<Theory> findAllTheoriesByParam(Pageable pageable, String name, String topicName,
        String userName);
}

