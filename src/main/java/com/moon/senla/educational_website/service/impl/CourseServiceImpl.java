package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.course.CoursePageDto;
import com.moon.senla.educational_website.service.CourseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    @Autowired
    private final CourseRepository courseRepository;

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
    public CoursePageDto findAllPageable(Pageable pageable) {
        Page<Course> page = courseRepository.findAll(pageable);
        return new CoursePageDto(page.getContent(), pageable.getPageNumber(), page.getTotalPages());
    }

    @Override
    public void deleteById(long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> findAllCourseByTopicId(long id) {
        List<Course> allCourses = courseRepository.findAll();
        List<Course> courses = new ArrayList<>();
        for (Course course : allCourses) {
            if (course.getTopic().getId().equals(id)) {
                courses.add(course);
            }
        }
        return courses;
    }

    @Override
    public CoursePageDto findAllCoursesByParam(Pageable pageable, String name, Topic topic,
        User user) {
        Page<Course> page = courseRepository.findAll(pageable);
        List<Course> allContent = page.getContent();
        List<Course> courses = new ArrayList<>();
        for (Course course : allContent) {
            if (course.getName().equals(name)) {
                courses.add(course);
            }
            if (topic != null && course.getTopic().getName().equals(topic.getName())) {
                courses.add(course);
            }
            if (user != null && course.getUser().getUsername().equals(user.getUsername())) {
                courses.add(course);
            }
        }
        return new CoursePageDto(courses, pageable.getPageNumber(), page.getTotalPages());
    }
}
