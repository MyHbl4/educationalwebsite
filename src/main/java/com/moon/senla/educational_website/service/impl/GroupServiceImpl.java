package com.moon.senla.educational_website.service.impl;


import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.educational_website.utils.StringConstants.COURSE_NF;
import static com.moon.senla.educational_website.utils.StringConstants.GROUP_NF;
import static com.moon.senla.educational_website.utils.StringConstants.USER_NF;

import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.dao.GroupRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.GroupService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository,
        UserRepository userRepository,
        CourseRepository courseRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Group save(Principal principal, Group newGroup) {
        Course course = checkRequest(principal, newGroup.getCourse().getId());
        newGroup.setCourse(course);
        newGroup.setAvailable(newGroup.getCapacity());
        try {
            return groupRepository.save(newGroup);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, group could not be saved");
        }
    }

    @Override
    public Group findById(long id) {
        return groupRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, GROUP_NF.value));
    }

    @Override
    public Page<Group> findAll(Pageable pageable) {
        try {
            return groupRepository.findAll(pageable);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, groups cannot be found");
        }
    }

    @Override
    public void deleteById(long id) {
        if (!groupRepository.findById(id).isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND, GROUP_NF.value);
        }
        try {
            groupRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                COULD_NOT_DELETE.value);
        }
    }

    @Override
    public Page<Group> findAllGroupsByCourseId(Pageable pageable, long id) {
        if (!courseRepository.findById(id).isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND, COURSE_NF.value);
        }
        try {
            return groupRepository.findAllByCourseId(pageable, id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, groups cannot be found");
        }
    }

    @Override
    public Group update(Principal principal, Group groupDto) {
        Group oldGroup = groupRepository.findById(groupDto.getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, GROUP_NF.value));
        checkRequest(principal, oldGroup.getCourse().getId());
        oldGroup.setName(groupDto.getName());
        try {
            return groupRepository.save(oldGroup);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, group could not be updated");
        }
    }

    private Course checkRequest(Principal principal, Long id) {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, COURSE_NF.value));
        User user = userRepository.findById(course.getUser().getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, USER_NF.value));
        if (!principal.getName().equals(user.getUsername())) {
            throw new CustomException(HttpStatus.FORBIDDEN,
                "Invalid request, access is denied");
        }
        return course;
    }
}