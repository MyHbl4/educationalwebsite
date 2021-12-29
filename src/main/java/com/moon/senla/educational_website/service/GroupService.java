package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Group;
import java.util.List;

public interface GroupService {

    Group save(Group group);

    Group findById(long id);

    List<Group> findAll();

    void deleteById(long id);
}

