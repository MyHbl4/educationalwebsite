package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.dao.TheoryRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.SearchFilterService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SearchFilterServiceImpl implements SearchFilterService {

    private final CourseRepository courseRepository;
    private final TheoryRepository theoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public SearchFilterServiceImpl(CourseRepository courseRepository,
        TheoryRepository theoryRepository,
        UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.theoryRepository = theoryRepository;
        this.userRepository = userRepository;
    }

    public Page<Course> findAllCoursesByParam(Pageable pageable, String name, String topicName,
        String authorName) {
        try {
            Page<Course> page = courseRepository.findAll(pageable);
            List<Course> allContent = page.getContent();
            List<Course> courses = new ArrayList<>();
            for (Course course : allContent) {
                if (name == null && topicName == null && authorName == null) {
                    courses.add(course);
                } else if (name == null && topicName == null && course.getUser().getUsername()
                    .equals(authorName)) {
                    courses.add(course);
                } else if (name == null && course.getTopic().getName().equals(topicName)
                    && authorName == null) {
                    courses.add(course);
                } else if (name == null && course.getTopic().getName().equals(topicName)
                    && course.getUser().getUsername().equals(authorName)) {
                    courses.add(course);
                } else if (course.getName().equals(name) && topicName == null
                    && authorName == null) {
                    courses.add(course);
                } else if (course.getName().equals(name) && topicName == null && course.getUser()
                    .getUsername().equals(authorName)) {
                    courses.add(course);
                } else if (course.getName().equals(name) && course.getTopic().getName()
                    .equals(topicName) && authorName == null) {
                    courses.add(course);
                } else if (course.getName().equals(name) && course.getTopic().getName()
                    .equals(topicName) && course.getUser().getUsername().equals(authorName)) {
                    courses.add(course);
                }
            }
            return new PageImpl<>(courses, pageable, courses.size());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, courses cannot be found");
        }
    }

    public Page<Theory> findAllTheoriesByParam(Pageable pageable, String name, String topicName,
        String authorName) {
        try {
            Page<Theory> page = theoryRepository.findAll(pageable);
            List<Theory> allContent = page.getContent();
            List<Theory> theories = new ArrayList<>();
            for (Theory theory : allContent) {
                if (name == null && topicName == null && authorName == null) {
                    theories.add(theory);
                } else if (name == null && topicName == null && theory.getUser().getUsername()
                    .equals(authorName)) {
                    theories.add(theory);
                } else if (name == null && theory.getTopic().getName().equals(topicName)
                    && authorName == null) {
                    theories.add(theory);
                } else if (name == null && theory.getTopic().getName().equals(topicName)
                    && theory.getUser().getUsername().equals(authorName)) {
                    theories.add(theory);
                } else if (theory.getName().equals(name) && topicName == null
                    && authorName == null) {
                    theories.add(theory);
                } else if (theory.getName().equals(name) && topicName == null && theory.getUser()
                    .getUsername().equals(authorName)) {
                    theories.add(theory);
                } else if (theory.getName().equals(name) && theory.getTopic().getName()
                    .equals(topicName) && authorName == null) {
                    theories.add(theory);
                } else if (theory.getName().equals(name) && theory.getTopic().getName()
                    .equals(topicName) && theory.getUser().getUsername().equals(authorName)) {
                    theories.add(theory);
                }
            }
            return new PageImpl<>(theories, pageable, theories.size());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, theories cannot be found");
        }
    }

    @Override
    public Page<User> findAllUsersByParam(Pageable pageable, String firstName, String lastName) {
        Page<User> page = userRepository.findAll(pageable);
        List<User> allContent = page.getContent();
        List<User> users = new ArrayList<>();
        for (User user : allContent) {
            if (firstName == null && lastName == null) {
                users.add(user);
            } else if (firstName == null && user.getLastName()
                .equals(lastName)) {
                users.add(user);
            } else if (user.getFirstName().equals(firstName) && lastName == null) {
                users.add(user);
            } else if (user.getFirstName().equals(firstName) && user.getLastName()
                .equals(lastName)) {
                users.add(user);
            }
        }
        return new PageImpl<>(users, pageable, users.size());
    }
}
