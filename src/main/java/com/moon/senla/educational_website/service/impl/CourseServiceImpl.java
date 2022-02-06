package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.service.CourseService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
        try {
            return courseRepository.save(course);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, course could not be saved");
        }
    }

    @Override
    public Course findById(long id) {
        Course course = null;
        Optional<Course> option = courseRepository.findById(id);
        if (option.isPresent()) {
            course = option.get();
        }
        if (course == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Course Not Found");
        }
        return course;
    }

    @Override
    public Page<Course> findAll(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);
        if (courses.getContent().isEmpty()) {
            throw new CustomException(HttpStatus.NO_CONTENT,
                "Request has been successfully processed and the response is  blank. Courses Not Found");
        }
        return courses;
    }

    @Override
    public void deleteById(long id) {
        try {
            courseRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Course Not Found");
        }
    }

}
