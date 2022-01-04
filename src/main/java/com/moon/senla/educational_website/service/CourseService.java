package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.course.CoursePageDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    Course save(Course course);

    Course findById(long id);

    Page<Course> findAll(Pageable pageable);

    CoursePageDto findAllPageable(Pageable pageable);

    void deleteById(long id);

    List<Course> findAllCourseByTopicId(long id);

    CoursePageDto findAllCoursesByParam(Pageable pageable, String name, Topic topic, User user);

}

