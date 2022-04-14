package com.moon.senla.educational_website.service.impl;

import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.educational_website.utils.StringConstants.USER_NF;

import com.moon.senla.educational_website.dao.GroupRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.NotFoundException;
import com.moon.senla.educational_website.error.ValidationException;
import com.moon.senla.educational_website.model.Status;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
        GroupRepository groupRepository,
        MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.mongoTemplate = mongoTemplate;
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
    public User findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Id").is(id));
        User user = mongoTemplate.findOne(query, User.class);
        if (user == null) {
            log.warn("findById - no user found by id: {}", id);
            throw new NotFoundException(USER_NF.value);
        }
        log.info("findById - user: {} found by id: {}", user.getUsername(), id);
        return user;
    }

    @Override
    public User deleteById(String id) {
        User user = findById(id);
        user.setStatus(Status.DELETED);
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_DELETE.value);
        }
    }

    @Override
    public Page<User> findAll(PageRequest pageable) {
        return userRepository.findAll(pageable);
    }

//    @Override
//    public Page<User> getAllUsersByGroupId(Pageable pageable, long groupId) {
//        if (!groupRepository.findById(groupId).isPresent()) {
//            throw new NotFoundException(GROUP_NF.value);
//        }
//        try {
//            return userRepository.getAllUsersByGroupId(pageable, groupId);
//        } catch (Exception e) {
//            throw new NotFoundException(USER_NF.value);
//        }
//    }
//
//    @Override
//    public Page<User> findAllUsersByParam(Pageable pageable, String firstName, String lastName) {
//        try {
//            return userRepository.findAllUsersByParam(pageable, firstName, lastName);
//        } catch (Exception e) {
//            throw new NotFoundException(USER_NF.value);
//        }
//    }
}
