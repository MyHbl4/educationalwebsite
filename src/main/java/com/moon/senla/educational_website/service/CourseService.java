package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
import java.util.ArrayList;
import java.util.List;

public interface CourseService {

    Course save(Course course);

    Course findById(long id);

    List<Course> findAll();

    void deleteById(long id);

    List<Course> findAllCourseByTopicId(long id);

    List<Course> findAllCoursesByParam(String name, Topic topic, User user);
}

