package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User save(User user);

    User findByUsername(String username);

    User findById(long id);

    Page<User> findAll(Pageable pageable);

    void deleteById(long id);

}
