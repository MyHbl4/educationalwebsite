package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.GroupRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.service.ManagingSubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public void addUserToGroup(String username, long groupId) {
        Group group = groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Group Not Found"));
        if (group.getAvailable() == 0) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, there's no available seats, sorry");
        }
        try {
            long userId = userRepository.findByUsername(username).getId();
            userRepository.addUserToGroup(userId, groupId);
            group.setAvailable(group.getAvailable() - 1);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, subscription failed");
        }
    }

    @Transactional
    @Override
    public void removeUserFromGroup(String username, long groupId) {
        Group group = groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Group Not Found"));
        group.getUsers().stream().filter(u -> u.getUsername().equals(username)).findAny()
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "User Not Found"));
        try {
            long userId = userRepository.findByUsername(username).getId();
            userRepository.removeUserFromGroup(userId, groupId);
            group.setAvailable(group.getAvailable() + 1);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, unsubscribe failed");
        }
    }
}
