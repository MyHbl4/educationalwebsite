package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.GroupRepository;
import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.service.GroupService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

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
    public Page<Group> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Override
    public void deleteById(long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public Page<Group> findAllGroupsByCourse_Id(Pageable pageable, long id) {
        return groupRepository.findAllByCourse_Id(pageable, id);
    }
}