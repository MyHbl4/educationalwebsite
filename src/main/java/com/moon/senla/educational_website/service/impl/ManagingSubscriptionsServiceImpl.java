package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.service.ManagingSubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addUserToGroup(long userId, long groupId) {
        userRepository.addUserToGroup(userId, groupId);
    }

    @Override
    public void removeUserFromGroup(long userId, long groupId) {
        userRepository.removeUserFromGroup(userId, groupId);
    }
}
