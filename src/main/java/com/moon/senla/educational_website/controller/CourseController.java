package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.CourseDto;
import com.moon.senla.educational_website.model.dto.mapper.CourseMapper;
import com.moon.senla.educational_website.service.CourseService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public List<CourseDto> findAll(@PageableDefault(sort = {"name"}, size = 5)
        Pageable pageable) {
        log.info("find all courses ");
        Page<Course> pageCourse = courseService.findAll(pageable);
        List<Course> course = pageCourse.getContent();
        return CourseMapper.INSTANCE.listToDtoList(course);
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
    public CourseDto update(@RequestBody Course courseToUpdate) {
        log.info("update course {}", courseToUpdate);
        Course course = courseService.save(courseToUpdate);
        return CourseMapper.INSTANCE.courseToCourseDto(course);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete course by id {}", id);
        courseService.deleteById(id);
    }

    @GetMapping(path = "/topics/{id}")
    public List<CourseDto> findAllCourseByTopicId(@PathVariable(name = "id") long id) {
        log.info("find course by topic id {}", id);
        List<Course> course = courseService.findAllCourseByTopicId(id);
        return CourseMapper.INSTANCE.listToDtoList(course);
    }

    @GetMapping(path = "/find-needed")
    public List<CourseDto> findAllCoursesByParam(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "topic", required = false) Topic topic,
        @RequestParam(value = "user", required = false) User user) {
        List<Course> course = courseService.findAllCoursesByParam(name, topic, user);
        return CourseMapper.INSTANCE.listToDtoList(course);
    }
}