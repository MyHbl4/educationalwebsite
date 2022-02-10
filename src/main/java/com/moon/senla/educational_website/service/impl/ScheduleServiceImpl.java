package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.dao.GroupRepository;
import com.moon.senla.educational_website.dao.ScheduleRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.Schedule;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.mapper.ScheduleMapper;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleNewDto;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleUpdateDto;
import com.moon.senla.educational_website.service.ScheduleService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public ScheduleServiceImpl(
        ScheduleRepository scheduleRepository,
        GroupRepository groupRepository,
        CourseRepository courseRepository,
        UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Schedule save(Principal principal, ScheduleNewDto schedule) {
        Group group = checkRequest(principal, schedule.getGroup().getId());
        Schedule newSchedule = ScheduleMapper.INSTANCE.scheduleNewDtoToSchedule(schedule);
        newSchedule.setGroup(group);
        newSchedule.setDate(schedule.getDate());
        try {
            return scheduleRepository.save(newSchedule);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, schedule could not be saved");
        }
    }

    @Override
    public Schedule findById(long id) {
        return scheduleRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Schedule Not Found"));
    }

    @Override
    public Page<Schedule> findAll(Pageable pageable) {
        try {
            return scheduleRepository.findAll(pageable);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, schedules cannot be found");
        }
    }

    @Override
    public void deleteById(Principal principal, long id) {
        Schedule oldSchedule = scheduleRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Schedule Not Found"));
        checkRequest(principal, oldSchedule.getGroup().getId());
        try {
            scheduleRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Schedule Not Found");
        }
    }

    @Override
    public Page<Schedule> findAllByGroup_Id(Pageable pageable, long groupId) {
        groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Group Not Found"));
        try {
            return scheduleRepository.findAllByGroup_Id(pageable, groupId);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, schedules cannot be found");
        }
    }

    @Override
    public Schedule update(Principal principal, ScheduleUpdateDto schedule) {
        Schedule oldSchedule = scheduleRepository.findById(schedule.getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Schedule Not Found"));
        checkRequest(principal, oldSchedule.getGroup().getId());
        oldSchedule.setDate(schedule.getDate());
        try {
            return scheduleRepository.save(oldSchedule);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, schedule could not be updated");
        }
    }

    private Group checkRequest(Principal principal, Long id) {
        Group group = groupRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Group Not Found"));
        Course course = courseRepository.findById(group.getCourse().getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Course Not Found"));
        User user = userRepository.findById(course.getUser().getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "User Not Found"));
        if (!user.getUsername().equals(principal.getName())) {
            throw new CustomException(HttpStatus.FORBIDDEN,
                "Invalid request, access is denied");
        }
        return group;
    }
}
