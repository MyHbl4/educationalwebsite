package com.moon.senla.businessservice.service.impl;

import static com.moon.senla.businessservice.utils.StringConstants.USER_NF;

import com.moon.senla.businessservice.dao.UserRepository;
import com.moon.senla.businessservice.error.CustomException;
import com.moon.senla.businessservice.model.Group;
import com.moon.senla.businessservice.model.User;
import com.moon.senla.businessservice.service.GroupService;
import com.moon.senla.businessservice.service.ManagingSubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagingSubscriptionsServiceImpl implements ManagingSubscriptionsService {

    private final UserRepository userRepository;
    private final GroupService groupService;

    @Autowired
    public ManagingSubscriptionsServiceImpl(
        UserRepository userRepository,
        GroupService groupService) {
        this.userRepository = userRepository;
        this.groupService = groupService;
    }

    @Transactional
    @Override
    public void addUserToGroup(String username, long groupId) {
        Group group = groupService.findById(groupId);
        if (group.getAvailable() == 0) {
            throw new CustomException("Invalid request, there's no available seats, sorry");
        }
        try {
            long userId = userRepository.findByUsername(username).getId();
            userRepository.addUserToGroup(userId, groupId);
            group.setAvailable(group.getAvailable() - 1);
        } catch (Exception e) {
            throw new CustomException("Invalid request, subscription failed");
        }
    }

    @Transactional
    @Override
    public void removeUserFromGroup(long userId, long groupId) {
        Group group = groupService.findById(groupId);
        User user = group.getUsers().stream().filter(u -> u.getId() == (userId))
            .findAny()
            .orElseThrow(() -> new CustomException(USER_NF.value));
        try {
            userRepository.removeUserFromGroup(user.getId(), groupId);
            group.setAvailable(group.getAvailable() + 1);
        } catch (Exception e) {
            throw new CustomException("Invalid request, unsubscribe failed");
        }
    }

    @Transactional
    @Override
    public void unsubscribeUserFromGroup(String username, long groupId) {
        Group group = groupService.findById(groupId);
        User user = group.getUsers().stream().filter(u -> u.getUsername().equals(username))
            .findAny()
            .orElseThrow(() -> new CustomException(USER_NF.value));
        try {
            userRepository.removeUserFromGroup(user.getId(), groupId);
            group.setAvailable(group.getAvailable() + 1);
        } catch (Exception e) {
            throw new CustomException("Invalid request, unsubscribe failed");
        }
    }
}
