package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.GroupRepository;
import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.service.GroupService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    @Autowired
    private final GroupRepository groupRepository;

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group findById(long id) {
        Group group = null;
        Optional<Group> option = groupRepository.findById(id);
        if (option.isPresent()) {
            group = option.get();
        }
        return group;
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        groupRepository.deleteById(id);
    }
}