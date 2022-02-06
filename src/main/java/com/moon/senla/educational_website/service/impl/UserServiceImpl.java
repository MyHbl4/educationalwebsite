package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.UserService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public UserServiceImpl(UserRepository userRepository,
        CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        if (result == null) {
            log.warn("IN findByUsername - no user found by username: {}", username);
            throw new CustomException(HttpStatus.NOT_FOUND, "User Not Found");
        }
        log.info("IN findByUsername - user: {} found by username: {}", result.getUsername(),
            username);
        return result;
    }

    @Override
    public User findById(long id) {
        User user = null;
        Optional<User> option = userRepository.findById(id);
        if (option.isPresent()) {
            user = option.get();
        }
        if (user == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "User Not Found");
        }
        return user;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        if (users.getContent().isEmpty()) {
            throw new CustomException(HttpStatus.NO_CONTENT,
                "Request has been successfully processed and the response is  blank. Users Not Found");
        }
        return users;
    }

    @Override
    public void deleteById(long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.NOT_FOUND, "User Not Found");
        }
    }

    @Override
    public Page<Course> findAllCoursesByUsername(Pageable pageable, String username) {
        try {
            Page<Course> courses = courseRepository.findAllCoursesByUsername(pageable, username);
            if (courses.getContent().isEmpty()) {
                throw new CustomException(HttpStatus.NO_CONTENT,
                    "Courses by this user id Not Found");
            }
            return courses;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, courses cannot be found");
        }
    }
}
