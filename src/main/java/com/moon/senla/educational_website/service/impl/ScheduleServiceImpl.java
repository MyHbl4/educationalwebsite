package com.moon.senla.educational_website.service.impl;

import static com.moon.senla.educational_website.utils.StringConstants.ACCESS_DENIED;
import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_SAVED;
import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_UPDATED;
import static com.moon.senla.educational_website.utils.StringConstants.SCHEDULE_NF;

import com.moon.senla.educational_website.dao.ScheduleRepository;
import com.moon.senla.educational_website.error.AuthException;
import com.moon.senla.educational_website.error.NotFoundException;
import com.moon.senla.educational_website.error.ValidationException;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.Schedule;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.CourseService;
import com.moon.senla.educational_website.service.GroupService;
import com.moon.senla.educational_website.service.ScheduleService;
import com.moon.senla.educational_website.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final GroupService groupService;
    private final CourseService courseService;
    private final UserService userService;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ScheduleServiceImpl(
        ScheduleRepository scheduleRepository,
        GroupService groupService,
        CourseService courseService,
        UserService userService, MongoTemplate mongoTemplate) {
        this.scheduleRepository = scheduleRepository;
        this.groupService = groupService;
        this.courseService = courseService;
        this.userService = userService;
        this.mongoTemplate = mongoTemplate;
    }

//    @Override
//    public Schedule save(Principal principal, Schedule schedule) {
//        Group group = checkRequest(principal, schedule.getGroup().getId());
//        schedule.setGroup(group);
//        schedule.setDate(schedule.getDate());
//        try {
//            return scheduleRepository.save(schedule);
//        } catch (Exception e) {
//            throw new ValidationException(COULD_NOT_SAVED.value);
//        }
//    }
//
//    @Override
//    public Schedule findById(long id) {
//        return scheduleRepository.findById(id)
//            .orElseThrow(() -> new NotFoundException(SCHEDULE_NF.value));
//    }
//
//    @Override
//    public Page<Schedule> findAll(Pageable pageable) {
//        try {
//            return scheduleRepository.findAll(pageable);
//        } catch (Exception e) {
//            throw new NotFoundException(SCHEDULE_NF.value);
//        }
//    }
//
//    @Override
//    public void deleteById(Principal principal, long id) {
//        if (!scheduleRepository.findById(id).isPresent()) {
//            throw new NotFoundException(SCHEDULE_NF.value);
//        }
//        checkRequest(principal, id);
//        try {
//            scheduleRepository.deleteById(id);
//        } catch (Exception e) {
//            throw new ValidationException(COULD_NOT_DELETE.value);
//        }
//    }
//
//    @Override
//    public Page<Schedule> findAllByGroupId(Pageable pageable, long groupId) {
//        Group group = groupService.findById(groupId);
//        try {
//            return scheduleRepository.findAllByGroupId(pageable, group.getId());
//        } catch (Exception e) {
//            throw new NotFoundException(SCHEDULE_NF.value);
//        }
//    }
//
//    @Override
//    public Schedule update(Principal principal, Schedule schedule) {
//        Schedule oldSchedule = scheduleRepository.findById(schedule.getId())
//            .orElseThrow(() -> new NotFoundException(SCHEDULE_NF.value));
//        checkRequest(principal, oldSchedule.getGroup().getId());
//        oldSchedule.setDate(schedule.getDate());
//        try {
//            return scheduleRepository.save(oldSchedule);
//        } catch (Exception e) {
//            throw new ValidationException(COULD_NOT_UPDATED.value);
//        }
//    }
//
//    private Group checkRequest(Principal principal, Long id) {
//        Group group = groupService.findById(id);
//        Course course = courseService.findById(group.getCourse().getId());
//        User user = userService.findById(course.getUser().getId());
//        if (!user.getUsername().equals(principal.getName())) {
//            throw new AuthException(ACCESS_DENIED.value);
//        }
//        return group;
//    }
}
