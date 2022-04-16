package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User findByUsername(String username);

    User findById(String id);

    User deleteById(String id);

    Page<User> findAll(PageRequest pageable);

    User save(User user);

    Page<User> getAllUsersByGroupId(Pageable pageable, String groupId);

//    Page<User> findAllUsersByParam(Pageable pageable, String firstName, String lastName);
}