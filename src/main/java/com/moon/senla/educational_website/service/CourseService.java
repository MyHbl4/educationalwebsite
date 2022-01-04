package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    Course save(Course course);

    Course findById(long id);

    Page<Course> findAll(Pageable pageable);

    void deleteById(long id);

    List<Course> findAllCourseByTopicId(long id);

    List<Course> findAllCoursesByParam(String name, Topic topic, User user);
}

