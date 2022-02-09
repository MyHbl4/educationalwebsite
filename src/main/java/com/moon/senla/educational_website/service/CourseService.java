package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.dto.course.CourseNewDto;
import com.moon.senla.educational_website.model.dto.course.CourseUpdateDto;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    Course save(Principal principal, CourseNewDto course);

    Course findById(long id);

    Page<Course> findAll(Pageable pageable);

    void deleteById(long id);

    Course update(Principal principal, CourseUpdateDto course);

    Page<Course> findAllCoursesByUsername(Pageable pageable, String username);
}

