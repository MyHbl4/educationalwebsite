package com.moon.senla.businessservice.service.impl;

import static com.moon.senla.businessservice.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.businessservice.utils.StringConstants.GROUP_NF;
import static com.moon.senla.businessservice.utils.StringConstants.USER_NF;

import com.moon.senla.businessservice.dao.GroupRepository;
import com.moon.senla.businessservice.dao.UserRepository;
import com.moon.senla.businessservice.error.NotFoundException;
import com.moon.senla.businessservice.error.ValidationException;
import com.moon.senla.businessservice.model.Status;
import com.moon.senla.businessservice.model.User;
import com.moon.senla.businessservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
        GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        if (result == null) {
            log.warn("findByUsername - no user found by username: {}", username);
            throw new NotFoundException(USER_NF.value);
        }
        log.info("findByUsername - user: {} found by username: {}", username,
            username);
        return result;
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(USER_NF.value));
    }


    @Override
    public User deleteById(long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(USER_NF.value));
        user.setStatus(Status.DELETED);
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_DELETE.value);
        }
    }

    @Override
    public Page<User> getAllUsersByGroupId(Pageable pageable, long groupId) {
        if (!groupRepository.findById(groupId).isPresent()) {
            throw new NotFoundException(GROUP_NF.value);
        }
        try {
            return userRepository.getAllUsersByGroupId(pageable, groupId);
        } catch (Exception e) {
            throw new NotFoundException(USER_NF.value);
        }
    }

    @Override
    public Page<User> findAllUsersByParam(Pageable pageable, String firstName, String lastName) {
        try {
            return userRepository.findAllUsersByParam(pageable, firstName, lastName);
        } catch (Exception e) {
            throw new NotFoundException(USER_NF.value);
        }
    }
}
