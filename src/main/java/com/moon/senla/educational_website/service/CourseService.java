package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    Course save(Course course);

    Course findById(long id);

    Page<Course> findAll(Pageable pageable);

    void deleteById(long id);

    Page<Course> findAllCoursesByParam(Pageable pageable, String name, String topicName,
        String userName);
}

