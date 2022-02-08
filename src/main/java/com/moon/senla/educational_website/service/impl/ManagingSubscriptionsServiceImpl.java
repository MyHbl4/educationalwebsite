package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.GroupRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.service.ManagingSubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ManagingSubscriptionsServiceImpl implements ManagingSubscriptionsService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public ManagingSubscriptionsServiceImpl(
        UserRepository userRepository,
        GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void addUserToGroup(String username, long groupId) {
        groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Group Not Found"));
        try {
            long userId = userRepository.findByUsername(username).getId();
            userRepository.addUserToGroup(userId, groupId);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, subscription failed");
        }
    }

    @Override
    public void removeUserFromGroup(String username, long groupId) {
        groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Group Not Found"));
        try {
            long userId = userRepository.findByUsername(username).getId();
            userRepository.removeUserFromGroup(userId, groupId);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, unsubscribe failed");
        }
    }
}
