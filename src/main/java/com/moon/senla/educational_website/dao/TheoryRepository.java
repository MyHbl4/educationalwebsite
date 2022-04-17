package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Theory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheoryRepository extends MongoRepository<Theory, String> {

    Page<Theory> findAll(Pageable pageable);

    void deleteById(String id);
}
