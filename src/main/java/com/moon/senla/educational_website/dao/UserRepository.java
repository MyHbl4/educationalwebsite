package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    Page<User> findAll(Pageable pageable);

    User findByUsername(String username);

    User findUserByEmail(String email);

    Page<User> getAllUsersByGroups(Pageable pageable, String groupId);
}

