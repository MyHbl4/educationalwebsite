package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    Page<User> findAll(Pageable pageable);

    User findByUsername(String username);

    User findUserByEmail(String email);

//    @Query(
//        value =
//            "select id, email, username, first_name, last_name, password, status, group_id, user_id from Users u full join user_group ug ON u.id = ug.user_id WHERE ug.group_id = (:group_id)",
//        nativeQuery = true)
//    Page<User> getAllUsersByGroupId(Pageable pageable, @Param("group_id") long groupId);
//
//    @Query("select u from User u where (u.firstName like :first_name or :first_name IS NULL) and (u.lastName like :last_name or :last_name IS NULL)")
//    Page<User> findAllUsersByParam(Pageable pageable, @Param("first_name") String firstName,
//        @Param("last_name") String lastname);
}

