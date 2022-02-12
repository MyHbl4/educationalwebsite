package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.course.CourseNewDto;
import com.moon.senla.educational_website.model.dto.course.CourseUpdateDto;
import com.moon.senla.educational_website.model.dto.mapper.CourseMapper;
import com.moon.senla.educational_website.service.CourseService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
        UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Course save(Principal principal, CourseNewDto newCourse) {
        try {
            Course course = CourseMapper.INSTANCE.courseNewDtoToCourse(newCourse);
            course.setUser(userRepository.findByUsername(principal.getName()));
            return courseRepository.save(course);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, course could not be saved");
        }
    }

    @Override
    public Course findById(long id) {
        return courseRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Course Not Found"));
    }

    @Override
    public Page<Course> findAll(Pageable pageable) {
        try {
            return courseRepository.findAll(pageable);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, courses cannot be found");
        }
    }

    @Override
    public void deleteById(long id) {
        courseRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Course Not Found"));
        try {
            courseRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, failed to delete");
        }
    }

    @Override
    public Course update(Principal principal, CourseUpdateDto courseToUpdate) {
        Course oldCourse = courseRepository.findById(courseToUpdate.getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Course Not Found"));
        User user = userRepository.findById(oldCourse.getUser().getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "User Not Found"));
        if (!user.getUsername().equals(principal.getName())) {
            throw new CustomException(HttpStatus.FORBIDDEN,
                "Invalid request, access is denied");
        }
        oldCourse.setName(courseToUpdate.getName());
        oldCourse.setPrice(courseToUpdate.getPrice());
        try {
            return courseRepository.save(oldCourse);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, course could not be updated");
        }
    }

    @Override
    public Page<Course> findAllCoursesByUsername(Pageable pageable, String username) {
        try {
            return courseRepository.findAllCoursesByUsername(pageable, username);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, courses cannot be found");
        }
    }

}
