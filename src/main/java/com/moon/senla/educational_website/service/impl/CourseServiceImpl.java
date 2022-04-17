package com.moon.senla.educational_website.service.impl;


import static com.moon.senla.educational_website.utils.StringConstants.ACCESS_DENIED;
import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_SAVED;
import static com.moon.senla.educational_website.utils.StringConstants.COURSE_NF;

import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.error.AuthException;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.error.NotFoundException;
import com.moon.senla.educational_website.error.ValidationException;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.CourseService;
import com.moon.senla.educational_website.service.TopicService;
import com.moon.senla.educational_website.service.UserService;
import java.security.Principal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;
    private final TopicService topicService;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
        UserService userService,
        TopicService topicService,
        MongoTemplate mongoTemplate) {
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.topicService = topicService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Course save(Principal principal, Course course) {
        Topic topic = topicService.findById(course.getTopic().getId());
        User user = userService.findByUsername(principal.getName());
        try {
            course.setUser(user);
            course.setTopic(topic);
            return courseRepository.save(course);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_SAVED.value);
        }
    }

    @Override
    public Course findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Id").is(id));
        Course course = mongoTemplate.findOne(query, Course.class);
        if (course == null) {
            log.warn("Course with id {} not found", id);
            throw new NotFoundException(COURSE_NF.value);
        }
        log.info("Course with id {} found", id);
        return course;
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
    public void deleteById(String id) {
        findById(id);
        try {
            courseRepository.deleteById(id);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_DELETE.value);
        }
    }

    @Override
    public Course update(Principal principal, Course courseToUpdate) {
        Course oldCourse = findById(courseToUpdate.getId());
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
            return courseRepository.findAllCoursesByUser_Username(pageable, username);
        } catch (Exception e) {
            throw new NotFoundException(COURSE_NF.value);
        }
    }

    @Override
    public Page<Course> findAllCoursesByParam(Pageable pageable, String name, String topicName,
        String authorName) {
        Query query = addCriteriaByNameTopicAuthor(name, topicName, authorName);
        List<Course> courses = mongoTemplate.find(query.with(pageable), Course.class);
        return new PageImpl<>(courses, pageable, courses.size());
    }

    static Query addCriteriaByNameTopicAuthor(String name, String topicName, String authorName) {
        Query query = new Query();
        if (name != null) {
            query.addCriteria(Criteria.where("name").regex(name, "i"));
        }
        if (topicName != null) {
            query.addCriteria(Criteria.where("topic.name").regex(topicName, "i"));
        }
        if (authorName != null) {
            query.addCriteria(Criteria.where("user.username").regex(authorName, "i"));
        }
        return query;
    }
}
