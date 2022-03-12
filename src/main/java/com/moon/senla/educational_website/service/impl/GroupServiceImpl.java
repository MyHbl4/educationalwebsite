package com.moon.senla.educational_website.service.impl;


import static com.moon.senla.educational_website.utils.StringConstants.ACCESS_DENIED;
import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_SAVED;
import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_UPDATED;
import static com.moon.senla.educational_website.utils.StringConstants.GROUP_NF;

import com.moon.senla.educational_website.dao.GroupRepository;
import com.moon.senla.educational_website.error.AuthException;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.error.NotFoundException;
import com.moon.senla.educational_website.error.ValidationException;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.CourseService;
import com.moon.senla.educational_website.service.GroupService;
import com.moon.senla.educational_website.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UserService userService;
    private final CourseService courseService;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository,
        UserService userService,
        CourseService courseService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
        this.courseService = courseService;
    }

    @Override
    public Group save(Principal principal, Group newGroup) {
        Course course = checkRequest(principal, newGroup.getCourse().getId());
        newGroup.setCourse(course);
        newGroup.setAvailable(newGroup.getCapacity());
        try {
            return groupRepository.save(newGroup);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_SAVED.value);
        }
    }

    @Override
    public Group findById(long id) {
        return groupRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(GROUP_NF.value));
    }

    @Override
    public Page<Group> findAll(Pageable pageable) {
        try {
            return groupRepository.findAll(pageable);
        } catch (Exception e) {
            throw new NotFoundException(GROUP_NF.value);
        }
    }

    @Override
    public void deleteById(long id) {
        if (!groupRepository.findById(id).isPresent()) {
            throw new NotFoundException(GROUP_NF.value);
        }
        try {
            groupRepository.deleteById(id);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_DELETE.value);
        }
    }

    @Override
    public Page<Group> findAllGroupsByCourseId(Pageable pageable, long id) {
        Course course = courseService.findById(id);
        try {
            return groupRepository.findAllByCourseId(pageable, course.getId());
        } catch (Exception e) {
            throw new NotFoundException(GROUP_NF.value);
        }
    }

    @Override
    public Group update(Principal principal, Group groupDto) {
        Group oldGroup = groupRepository.findById(groupDto.getId())
            .orElseThrow(() -> new CustomException(GROUP_NF.value));
        checkRequest(principal, oldGroup.getCourse().getId());
        oldGroup.setName(groupDto.getName());
        try {
            return groupRepository.save(oldGroup);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_UPDATED.value);
        }
    }

    private Course checkRequest(Principal principal, Long id) {
        Course course = courseService.findById(id);
        User user = userService.findById(course.getUser().getId());
        if (!principal.getName().equals(user.getUsername())) {
            throw new AuthException(ACCESS_DENIED.value);
        }
        return course;
    }
}