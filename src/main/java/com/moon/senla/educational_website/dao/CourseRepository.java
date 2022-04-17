package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {

    Page<Course> findAll(Pageable pageable);

    Page<Course> findAllCoursesByUser_Username(Pageable pageable, String username);

    void deleteById(String id);
}
