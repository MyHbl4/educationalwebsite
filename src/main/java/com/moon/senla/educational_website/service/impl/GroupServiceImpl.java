package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.dao.GroupRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.group.GroupNewDto;
import com.moon.senla.educational_website.model.dto.group.GroupShortDto;
import com.moon.senla.educational_website.model.dto.mapper.GroupMapper;
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
    public Group save(Principal principal, GroupNewDto group) {
        Group newGroup = GroupMapper.INSTANCE.groupNewDtoToGroup(group);
        Course course = checkRequest(principal, group.getCourse().getId());
        newGroup.setCourse(course);
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
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Group Not Found"));
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
        groupRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Group Not Found"));
        try {
            groupRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, failed to delete");
        }
    }

    @Override
    public Page<Group> findAllGroupsByCourse_Id(Pageable pageable, long id) {
        courseRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Course Not Found"));
        try {
            return groupRepository.findAllByCourse_Id(pageable, id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, groups cannot be found");
        }
    }

    @Override
    public Group update(Principal principal, GroupShortDto groupDto) {
        Group group = groupRepository.findById(groupDto.getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Group Not Found"));
        checkRequest(principal, group.getCourse().getId());
        group.setName(groupDto.getName());
        try {
            return groupRepository.save(group);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, group could not be updated");
        }
    }

    private Course checkRequest(Principal principal, Long id) {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Course Not Found"));
        User user = userRepository.findById(course.getUser().getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "User Not Found"));
        if (!principal.getName().equals(user.getUsername())) {
            throw new CustomException(HttpStatus.FORBIDDEN,
                "Invalid request, access is denied");
        }
        return course;
    }
}