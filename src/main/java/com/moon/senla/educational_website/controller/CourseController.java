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
import com.moon.senla.educational_website.service.SearchFilterService;
import io.swagger.annotations.Api;
import java.security.Principal;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private final SearchFilterService searchFilterService;
    private final GroupService groupService;
    private final FeedbackService feedbackService;

    public CourseController(CourseService courseService,
        SearchFilterService searchFilterService,
        GroupService groupService,
        FeedbackService feedbackService) {
        this.courseService = courseService;
        this.searchFilterService = searchFilterService;
        this.groupService = groupService;
        this.feedbackService = feedbackService;
    }

    @GetMapping()
    public Page<CourseDto> findAll(@PageableDefault(sort = {"id"}) Pageable pageable) {
        log.info("find all courses");
        return courseService.findAll(pageable)
            .map(CourseMapper.INSTANCE::courseToCourseDto);
    }

    @GetMapping(path = "/{id}")
    public CourseDto findById(@PathVariable(name = "id") long id) {
        log.info("find course by id {}", id);
        Course course = courseService.findById(id);
        return CourseMapper.INSTANCE.courseToCourseDto(course);
    }

    @PostMapping()
    public CourseDto save(Principal principal, @Valid @RequestBody CourseNewDto course) {
        log.info("save course: {}", course.getName());
        Course newCourse = courseService.save(principal, course);
        return CourseMapper.INSTANCE.courseToCourseDto(newCourse);
    }

    @PutMapping()
    public CourseDto update(Principal principal,
        @Valid @RequestBody CourseUpdateDto courseToUpdate) {
        log.info("update course: {}", courseToUpdate.getName());
        Course course = courseService.update(principal, courseToUpdate);
        return CourseMapper.INSTANCE.courseToCourseDto(course);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete course by id {}", id);
        courseService.deleteById(id);
    }

    @GetMapping(path = "/search")
    public Page<CourseDto> findAllCoursesByParam(
        @PageableDefault(sort = {"id"}) Pageable pageable,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "topic_name", required = false) String topicName,
        @RequestParam(value = "user_name", required = false) String authorName) {
        return searchFilterService.findAllCoursesByParam(pageable, name, topicName, authorName)
            .map(CourseMapper.INSTANCE::courseToCourseDto);
    }

    @GetMapping(path = "/{id}/groups")
    public Page<GroupDto> findAllGroupsByCourse_Id(@PathVariable(name = "id") long id,
        Pageable pageable) {
        log.info("find groups by course id {}", id);
        return groupService.findAllGroupsByCourse_Id(pageable, id)
            .map(GroupMapper.INSTANCE::groupToGroupDto);
    }

    @GetMapping(path = "/courses")
    public Page<CourseDto> findAllCoursesByUsername(
        Principal principal,
        Pageable pageable) {
        log.info("find courses where author is user: {}", principal.getName());
        return courseService.findAllCoursesByUsername(pageable, principal.getName())
            .map(CourseMapper.INSTANCE::courseToCourseDto);
    }

    @GetMapping(path = "/{id}/feedbacks")
    public Page<FeedbackDto> findAllFeedbacksByCourse_Id(@PathVariable(name = "id") long id,
        Pageable pageable) {
        log.info("find groups by course id {}", id);
        return feedbackService.getAllFeedbackByCourseId(pageable, id)
            .map(FeedbackMapper.INSTANCE::feedbackToFeedbackDto);
    }
}