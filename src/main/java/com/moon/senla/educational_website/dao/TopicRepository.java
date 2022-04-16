package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {

    Page<Topic> findAll(Pageable pageable);

    void deleteById(String id);
}
