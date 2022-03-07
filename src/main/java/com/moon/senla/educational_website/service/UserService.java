package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User findByUsername(String username);

    User findById(long id);

    User deleteById(long id);

    Page<User> getAllUsersByGroupId(Pageable pageable, long groupId);

    Page<User> findAllUsersByParam(Pageable pageable, String firstName, String lastName);
}
