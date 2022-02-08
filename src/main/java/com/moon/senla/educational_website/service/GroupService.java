package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.dto.group.GroupDto;
import com.moon.senla.educational_website.model.dto.group.GroupNewDto;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {

    Group save(Principal principal, GroupNewDto group);

    Group findById(long id);

    Page<Group> findAll(Pageable pageable);

    void deleteById(long id);

    Page<Group> findAllGroupsByCourse_Id(Pageable pageable, long id);

    Group update(Principal principal, GroupDto group);
}

