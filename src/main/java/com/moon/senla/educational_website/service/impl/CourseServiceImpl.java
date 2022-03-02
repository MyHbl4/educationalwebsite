package com.moon.senla.educational_website.service.impl;


import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.educational_website.utils.StringConstants.COURSE_NF;
import static com.moon.senla.educational_website.utils.StringConstants.USER_NF;

import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.CourseService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
        UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Course save(Principal principal, Course course) {
        try {
            course.setUser(userRepository.findByUsername(principal.getName()));
            return courseRepository.save(course);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, course could not be saved");
        }
    }

    @Override
    public Course findById(long id) {
        return courseRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, COURSE_NF.value));
    }

    @Override
    public Page<Course> findAll(Pageable pageable) {
        try {
            return courseRepository.findAll(pageable);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, courses cannot be found");
        }
    }

    @Override
    public void deleteById(long id) {
        if (!courseRepository.findById(id).isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND, COURSE_NF.value);
        }
        try {
            courseRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                COULD_NOT_DELETE.value);
        }
    }

    @Override
    public Course update(Principal principal, Course courseToUpdate) {
        Course oldCourse = courseRepository.findById(courseToUpdate.getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, COURSE_NF.value));
        User user = userRepository.findById(oldCourse.getUser().getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, USER_NF.value));
        if (!user.getUsername().equals(principal.getName())) {
            throw new CustomException(HttpStatus.FORBIDDEN,
                "Invalid request, access is denied");
        }
        oldCourse.setName(courseToUpdate.getName());
        oldCourse.setPrice(courseToUpdate.getPrice());
        try {
            return courseRepository.save(oldCourse);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, course could not be updated");
        }
    }

    @Override
    public Page<Course> findAllCoursesByUsername(Pageable pageable, String username) {
        try {
            return courseRepository.findAllCoursesByUsername(pageable, username);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, courses cannot be found");
        }
    }

}
