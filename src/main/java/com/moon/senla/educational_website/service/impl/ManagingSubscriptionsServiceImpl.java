package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.GroupRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.error.NotFoundException;
import com.moon.senla.educational_website.error.ValidationException;
import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.GroupService;
import com.moon.senla.educational_website.service.ManagingSubscriptionsService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagingSubscriptionsServiceImpl implements ManagingSubscriptionsService {

    private final UserRepository userRepository;
    private final GroupService groupService;
    private final GroupRepository groupRepository;

    @Autowired
    public ManagingSubscriptionsServiceImpl(
        UserRepository userRepository,
        GroupService groupService,
        GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupService = groupService;
        this.groupRepository = groupRepository;
    }

    @Transactional
    @Override
    public void addUserToGroup(String username, String groupId) {
        Group group = groupService.findById(groupId);
        User user = userRepository.findByUsername(username);
        if (group.getAvailable() == 0) {
            throw new ValidationException("Invalid request, there's no available seats, sorry");
        }
        List<User> users = group.getUsers();
        if (users.contains(user)) {
            throw new ValidationException("Invalid request, user already in group");
        }
        try {
            users.add(user);
            group.setUsers(users);
            group.setAvailable(group.getAvailable() - 1);
            user.getGroups().add(group);
            groupRepository.save(group);
            userRepository.save(user);
        } catch (Exception e) {
            throw new ValidationException("Invalid request, subscription failed");
        }
    }

    @Transactional
    @Override
    public void removeUserFromGroup(String userId, String groupId) {
        Group group = groupService.findById(groupId);
        User user = group.getUsers().stream().filter(u -> Objects.equals(u.getId(), userId))
            .findAny()
            .orElseThrow(
                () -> new NotFoundException("Invalid request, user don't belong to group"));
        try {
            group.getUsers().remove(user);
            group.setAvailable(group.getAvailable() + 1);
            user.getGroups().remove(group);
            userRepository.save(user);
            groupRepository.save(group);
        } catch (Exception e) {
            throw new CustomException("Invalid request, unsubscribe failed");
        }
    }

    @Transactional
    @Override
    public void unsubscribeUserFromGroup(String username, String groupId) {
        Group group = groupService.findById(groupId);
        User user = group.getUsers().stream().filter(u -> u.getUsername().equals(username))
            .findAny()
            .orElseThrow(
                () -> new NotFoundException("Invalid request, user don't belong to group"));
        try {
            group.getUsers().remove(user);
            group.setAvailable(group.getAvailable() + 1);
            user.getGroups().remove(group);
            userRepository.save(user);
            groupRepository.save(group);
        } catch (Exception e) {
            throw new CustomException("Invalid request, unsubscribe failed");
        }
    }
}
