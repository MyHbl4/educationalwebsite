package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.User;

public interface UserService {

    User findByUsername(String username);

    User findById(long id);

    void deleteById(long id);
}
