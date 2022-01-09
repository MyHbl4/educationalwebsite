package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {

    Group save(Group group);

    Group findById(long id);

    Page<Group> findAll(Pageable pageable);

    void deleteById(long id);
}

