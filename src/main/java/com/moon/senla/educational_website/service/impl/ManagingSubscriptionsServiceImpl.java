package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.ManagingSubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ManagingSubscriptionsServiceImpl implements ManagingSubscriptionsService {

    private final UserRepository userRepository;

    @Autowired
    public ManagingSubscriptionsServiceImpl(
        UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUserToGroup(String username, long groupId) {
        try {
            User user = userRepository.findByUsername(username);
            long userId = user.getId();
            userRepository.addUserToGroup(userId, groupId);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, subscription failed");
        }
    }

    @Override
    public void removeUserFromGroup(String username, long groupId) {
        try {
            User user = userRepository.findByUsername(username);
            long userId = user.getId();
            userRepository.removeUserFromGroup(userId, groupId);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, unsubscribe failed");
        }
    }
}
