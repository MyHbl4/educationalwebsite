package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.dto.course.CourseDto;
import com.moon.senla.educational_website.model.dto.mapper.CourseMapper;
import com.moon.senla.educational_website.service.CourseService;
import io.swagger.annotations.Api;
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

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
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
    public CourseDto save(@RequestBody Course course) {
        log.info("save course {}", course);
        Course newCourse = courseService.save(course);
        return CourseMapper.INSTANCE.courseToCourseDto(newCourse);
    }

    @PutMapping()
    @PreAuthorize("#courseToUpdate.user.username == authentication.name")
    public CourseDto update(@RequestBody Course courseToUpdate) {
        log.info("update course {}", courseToUpdate);
        Course course = courseService.save(courseToUpdate);
        return CourseMapper.INSTANCE.courseToCourseDto(course);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete course by id {}", id);
        courseService.deleteById(id);
    }

    @GetMapping(path = "/topics/{id}")
    public Page<CourseDto> findAllCourseByTopicId(@PathVariable(name = "id") long id,
        @PageableDefault(sort = {"id"}) Pageable pageable) {
        log.info("find course by topic id {}", id);
        return courseService.findAllCourseByTopicId(id, pageable)
            .map(CourseMapper.INSTANCE::courseToCourseDto);
    }

    @GetMapping(path = "/find-needed")
    public Page<CourseDto> findAllCoursesByParam(
        @PageableDefault(sort = {"id"}) Pageable pageable,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "topic_name", required = false) String topicName,
        @RequestParam(value = "user_name", required = false) String userName) {
        return courseService.findAllCoursesByParam(pageable, name, topicName, userName)
            .map(CourseMapper.INSTANCE::courseToCourseDto);
    }
}