package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.GroupRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.GroupService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository,
        UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Group save(Group group) {
        try {
            return groupRepository.save(group);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, group could not be saved");
        }
    }

    @Override
    public Group findById(long id) {
        Group group = null;
        Optional<Group> option = groupRepository.findById(id);
        if (option.isPresent()) {
            group = option.get();
        }
        if (group == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Group Not Found");
        }
        return group;
    }

    @Override
    public Page<Group> findAll(Pageable pageable) {
        Page<Group> groups = groupRepository.findAll(pageable);
        if (groups.getContent().isEmpty()) {
            throw new CustomException(HttpStatus.NO_CONTENT,
                "Request has been successfully processed and the response is  blank. Groups Not Found");
        }
        return groups;
    }

    @Override
    public void deleteById(long id) {
        try {
            groupRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Group Not Found");
        }
    }

    @Override
    public Page<Group> findAllGroupsByCourse_Id(Pageable pageable, long id) {
        try {
            Page<Group> groups = groupRepository.findAllByCourse_Id(pageable, id);
            if (groups.getContent().isEmpty()) {
                throw new CustomException(HttpStatus.NOT_FOUND,
                    "Groups by this course id Not Found");
            }
            return groups;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, groups cannot be found");
        }
    }

    @Override
    public Page<User> getAllUsersByGroup_Id(Pageable pageable, long groupId) {
        try {
            Page<User> users = userRepository.getAllUsersByGroup_Id(pageable, groupId);
            if (users.getContent().isEmpty()) {
                throw new CustomException(HttpStatus.NOT_FOUND,
                    "Users by this group id Not Found");
            }
            return users;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, users cannot be found");
        }
    }
}