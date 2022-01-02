package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.service.CourseService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<Course> findAll() {
        log.info("find all courses ");
        return courseService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course findById(@PathVariable(name = "id") long id) {
        log.info("find course by id {}", id);
        return courseService.findById(id);
    }

    @PostMapping()
    public Course save(@RequestBody Course course) {
        log.info("save course {}", course);
        return courseService.save(course);
    }

    @PutMapping()
    public Course update(@RequestBody Course courseToUpdate) {
        log.info("update course {}", courseToUpdate);
        return courseService.save(courseToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete course by id {}", id);
        courseService.deleteById(id);
    }

    @GetMapping(path = "/topics/{id}")
    public List<Course> findAllCourseByTopicId(@PathVariable(name = "id") long id) {
        log.info("find course by topic id {}", id);
        return courseService.findAllCourseByTopicId(id);
    }
}