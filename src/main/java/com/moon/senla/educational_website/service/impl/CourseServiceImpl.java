package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.service.CourseService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

}
