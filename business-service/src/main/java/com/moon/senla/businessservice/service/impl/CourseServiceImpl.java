package com.moon.senla.businessservice.service.impl;


import static com.moon.senla.businessservice.utils.StringConstants.ACCESS_DENIED;
import static com.moon.senla.businessservice.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.businessservice.utils.StringConstants.COULD_NOT_SAVED;
import static com.moon.senla.businessservice.utils.StringConstants.COURSE_NF;

import com.moon.senla.businessservice.dao.CourseRepository;
import com.moon.senla.businessservice.error.AuthException;
import com.moon.senla.businessservice.error.CustomException;
import com.moon.senla.businessservice.error.NotFoundException;
import com.moon.senla.businessservice.error.ValidationException;
import com.moon.senla.businessservice.model.Course;
import com.moon.senla.businessservice.model.User;
import com.moon.senla.businessservice.service.CourseService;
import com.moon.senla.businessservice.service.TopicService;
import com.moon.senla.businessservice.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;
    private final TopicService topicService;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
        UserService userService,
        TopicService topicService) {
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.topicService = topicService;
    }

    @Override
    public Course save(Principal principal, Course course) {
        try {
            course.setUser(userService.findByUsername(principal.getName()));
            course.setTopic(topicService.findById(course.getTopic().getId()));
            return courseRepository.save(course);
        } catch (Exception e) {
                throw new ValidationException(COULD_NOT_SAVED.value);
        }
    }

    @Override
    public Course findById(long id) {
        return courseRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(COURSE_NF.value));
    }

    @Override
    public Page<Course> findAll(Pageable pageable) {
        try {
            return courseRepository.findAll(pageable);
        } catch (Exception e) {
            throw new NotFoundException(COURSE_NF.value);
        }
    }

    @Override
    public void deleteById(long id) {
        if (!courseRepository.findById(id).isPresent()) {
            throw new NotFoundException(COURSE_NF.value);
        }
        try {
            courseRepository.deleteById(id);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_DELETE.value);
        }
    }

    @Override
    public Course update(Principal principal, Course courseToUpdate) {
        Course oldCourse = courseRepository.findById(courseToUpdate.getId())
            .orElseThrow(() -> new NotFoundException(COURSE_NF.value));
        User user = userService.findById(oldCourse.getUser().getId());
        if (!user.getUsername().equals(principal.getName())) {
            throw new AuthException(ACCESS_DENIED.value);
        }
        oldCourse.setName(courseToUpdate.getName());
        oldCourse.setPrice(courseToUpdate.getPrice());
        try {
            return courseRepository.save(oldCourse);
        } catch (Exception e) {
            throw new CustomException(ACCESS_DENIED.value);
        }
    }

    @Override
    public Page<Course> findAllCoursesByUsername(Pageable pageable, String username) {
        try {
            return courseRepository.findAllCoursesByUsername(pageable, username);
        } catch (Exception e) {
            throw new NotFoundException(COURSE_NF.value);
        }
    }

    @Override
    public Page<Course> findAllCoursesByParam(Pageable pageable, String name, String topicName,
        String authorName) {
        try {
            return courseRepository.findAllCoursesByParam(pageable, name, topicName, authorName);
        } catch (Exception e) {
            throw new NotFoundException(COURSE_NF.value);
        }
    }

}
