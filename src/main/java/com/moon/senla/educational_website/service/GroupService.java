package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {

    Group save(Group group);

    Group findById(long id);

    Page<Group> findAll(Pageable pageable);

    void deleteById(long id);

    Page<Group> findAllGroupsByCourse_Id(Pageable pageable, long id);

    Page<User> getAllUsersByGroup_Id(Pageable pageable, long groupId);
}

