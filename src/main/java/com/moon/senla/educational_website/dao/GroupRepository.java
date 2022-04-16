package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {

    Page<Group> findAll(Pageable pageable);

    Page<Group> findAllGroupsByCourseId(Pageable pageable, String courseId);

    void deleteById(String id);

}
