package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.UserService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            return null;
        }
        log.info("IN findByUsername - user: {} found by username: {}", result.getEmail(), username);
        return result;
    }

    @Override
    public User findById(long id) {
        User user = null;
        Optional<User> option = userRepository.findById(id);
        if (option.isPresent()) {
            user = option.get();
        }
        return user;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<Course> findAllCoursesByUserId(Pageable pageable, long userId) {
        return courseRepository.getCoursesByUser_Id(pageable, userId);
    }
}
