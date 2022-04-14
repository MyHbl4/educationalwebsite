package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Group;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {

    Group save(Principal principal, Group group);

    Group findById(String id);

    Page<Group> findAll(Pageable pageable);

    void deleteById(String id);

    Page<Group> findAllGroupsByCourseId(Pageable pageable, String id);

    Group update(Principal principal, Group group);
}

