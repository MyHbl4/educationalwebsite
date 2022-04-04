package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Course;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    Course save(Principal principal, Course course);

    Course findById(long id);

    Page<Course> findAll(Pageable pageable);

    void deleteById(long id);

    Course update(Principal principal, Course course);

    Page<Course> findAllCoursesByUsername(Pageable pageable, String username);

    Page<Course> findAllCoursesByParam(Pageable pageable, String name, String topicName,
        String authorName);
}

