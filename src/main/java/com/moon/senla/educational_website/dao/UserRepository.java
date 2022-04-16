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

//    @Query("select u from User u where (u.firstName like :first_name or :first_name IS NULL) and (u.lastName like :last_name or :last_name IS NULL)")
//    Page<User> findAllUsersByParam(Pageable pageable, @Param("first_name") String firstName,
//        @Param("last_name") String lastname);
}

