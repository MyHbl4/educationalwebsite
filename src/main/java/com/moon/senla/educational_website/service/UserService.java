package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.user.UserPageDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User save(User user);

    User findById(long id);

    Page<User> findAll(Pageable pageable);

    UserPageDto findAllPageable(Pageable pageable);

    void deleteById(long id);

    List<Course> findAllCoursesByUserId(long id);
}
