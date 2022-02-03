package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchFilterService {

    Page<Course> findAllCoursesByParam(Pageable pageable, String name, String topicName,
        String authorName);

    Page<Theory> findAllTheoriesByParam(Pageable pageable, String name, String topicName,
        String authorName);

    Page<User> findAllUsersByParam(Pageable pageable, String firstName, String lastName);

}
