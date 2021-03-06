package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.dto.course.CourseDto;
import com.moon.senla.educational_website.model.dto.course.CourseNewDto;
import com.moon.senla.educational_website.model.dto.course.CourseUpdateDto;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackDto;
import com.moon.senla.educational_website.model.dto.group.GroupDto;
import com.moon.senla.educational_website.model.dto.mapper.CourseMapper;
import com.moon.senla.educational_website.model.dto.mapper.FeedbackMapper;
import com.moon.senla.educational_website.model.dto.mapper.GroupMapper;
import com.moon.senla.educational_website.service.CourseService;
import com.moon.senla.educational_website.service.FeedbackService;
import com.moon.senla.educational_website.service.GroupService;
import io.swagger.annotations.Api;
import java.security.Principal;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/courses")
@Slf4j
@Api(tags = "Courses")
public class CourseController {

    private final CourseService courseService;
    private final GroupService groupService;
    private final FeedbackService feedbackService;
    private final CourseMapper courseMapper;
    private final GroupMapper groupMapper;
    private final FeedbackMapper feedbackMapper;

    public CourseController(CourseService courseService,
        GroupService groupService,
        FeedbackService feedbackService,
        CourseMapper courseMapper,
        GroupMapper groupMapper,
        FeedbackMapper feedbackMapper) {
        this.courseService = courseService;
        this.groupService = groupService;
        this.feedbackService = feedbackService;
        this.courseMapper = courseMapper;
        this.groupMapper = groupMapper;
        this.feedbackMapper = feedbackMapper;
    }

    @GetMapping(path = "/{id}")
    public CourseDto findById(@PathVariable(name = "id") long id) {
        log.info("findById - find course by id {}", id);
        Course course = courseService.findById(id);
        return courseMapper.courseToCourseDto(course);
    }

    @PostMapping()
    public CourseDto save(Principal principal, @Valid @RequestBody CourseNewDto course) {
        log.info("save - save course by name: {}", course.getName());
        Course newCourse = courseService.save(principal, courseMapper.courseNewDtoToCourse(course));
        return courseMapper.courseToCourseDto(newCourse);
    }

    @PutMapping()
    public CourseDto update(Principal principal,
        @Valid @RequestBody CourseUpdateDto courseToUpdate) {
        log.info("update - update course by id: {}", courseToUpdate.getId());
        Course course = courseService.update(principal,
            courseMapper.courseUpdateDtoToCourse(courseToUpdate));
        return courseMapper.courseToCourseDto(course);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete - delete course by id: {}", id);
        courseService.deleteById(id);
    }

    @GetMapping(path = "/search-by-param")
    public Page<CourseDto> findAllCoursesByParam(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "topic_name", required = false) String topicName,
        @RequestParam(value = "user_name", required = false) String authorName,
        @RequestParam int page) {
        PageRequest pageable = PageRequest.of(page, 5, Direction.ASC, "name");
        log.info("findAllCoursesByParam - find all courses by param");
        return courseService.findAllCoursesByParam(pageable, name, topicName, authorName)
            .map(courseMapper::courseToCourseDto);
    }

    @GetMapping(path = "/{id}/groups")
    public Page<GroupDto> findAllGroupsByCourseId(@PathVariable(name = "id") long id,
        @RequestParam int page) {
        PageRequest pageable = PageRequest.of(page, 5, Direction.ASC, "name");
        log.info("findAllGroupsByCourseId - find groups by course id {}", id);
        return groupService.findAllGroupsByCourseId(pageable, id)
            .map(groupMapper::groupToGroupDto);
    }

    @GetMapping(path = "/search-my-courses")
    public Page<CourseDto> findAllMyCourses(
        Principal principal, @RequestParam int page) {
        PageRequest pageable = PageRequest.of(page, 5, Direction.ASC, "c.name");
        log.info("findAllMyCourses - find courses where author is user: {}", principal.getName());
        return courseService.findAllCoursesByUsername(pageable, principal.getName())
            .map(courseMapper::courseToCourseDto);
    }

    @GetMapping(path = "/{id}/feedbacks")
    public Page<FeedbackDto> findAllFeedbacksByCourseId(@PathVariable(name = "id") long id,
        @RequestParam int page) {
        PageRequest pageable = PageRequest.of(page, 5, Direction.ASC, "date");
        log.info("findAllFeedbacksByCourseId - find groups by course id: {}", id);
        return feedbackService.getAllFeedbackByCourseId(pageable, id)
            .map(feedbackMapper::feedbackToFeedbackDto);
    }
}