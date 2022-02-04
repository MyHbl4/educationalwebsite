package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query(
        value =
            "insert into user_group(user_id, group_id) values ((:user_id), (:group_id))",
        nativeQuery = true)
    void addUserToGroup(@Param("user_id") long userId, @Param("group_id") long groupId);

    @Transactional
    @Modifying
    @Query(
        value =
            "delete from user_group where user_id = (:user_id) and group_id = (:group_id)",
        nativeQuery = true)
    void removeUserFromGroup(@Param("user_id") long userId, @Param("group_id") long groupId);
}

