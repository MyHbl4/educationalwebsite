package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.User;
import java.util.List;

public interface UserService {

    User save(User user);

    User findById(long id);

    List<User> findAll();

    void deleteById(long id);

    List<Course> findAllCoursesByUserId(long id);
}
