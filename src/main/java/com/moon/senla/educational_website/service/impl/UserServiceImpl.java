package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        if (result == null) {
            log.warn("IN findByUsername - no user found by username: {}", username);
            throw new CustomException(HttpStatus.NOT_FOUND, "User Not Found");
        }
        log.info("IN findByUsername - user: {} found by username: {}", username,
            username);
        return result;
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "User Not Found"));
    }


    @Override
    public void deleteById(long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.NOT_FOUND, "User Not Found");
        }
    }
}
