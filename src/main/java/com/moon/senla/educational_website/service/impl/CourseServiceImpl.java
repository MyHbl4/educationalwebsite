package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.service.CourseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course findById(long id) {
        Course course = null;
        Optional<Course> option = courseRepository.findById(id);
        if (option.isPresent()) {
            course = option.get();
        }
        return course;
    }

    @Override
    public Page<Course> findAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public void deleteById(long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Page<Course> findAllCoursesByParam(Pageable pageable, String name, String topicName,
        String userName) {
        Page<Course> page = courseRepository.findAll(pageable);
        List<Course> allContent = page.getContent();
        List<Course> courses = new ArrayList<>();
        for (Course course : allContent) {
            if (course.getName().equals(name)) {
                courses.add(course);
            } else if (topicName != null && course.getTopic().getName().equals(topicName)) {
                courses.add(course);
            } else if (userName != null && course.getUser().getUsername().equals(userName)) {
                courses.add(course);
            }
        }
        return new PageImpl<>(courses, pageable, courses.size());
    }
}
