package com.moon.senla.educational_website.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.Role;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.course.CourseDtoShort;
import com.moon.senla.educational_website.model.dto.course.CourseNewDto;
import com.moon.senla.educational_website.model.dto.course.CourseUpdateDto;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackNewDto;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackUpdateDto;
import com.moon.senla.educational_website.model.dto.topic.TopicDto;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private Principal principal;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    Role adminRole = new Role();
    Role userRole = new Role();
    List<Role> adminRoles = new ArrayList<>();
    List<Role> userRoles = new ArrayList<>();
    User admin = new User();
    User user = new User();
    List<User> listUsers = new ArrayList<>();
    List<Topic> listTopic = new ArrayList<>();
    List<Feedback> listFeedback = new ArrayList<>();
    List<Course> listCourses = new ArrayList<>();
    Group group = new Group();
    Course course = new Course();
    Topic topic = new Topic();
    Feedback feedback = new Feedback();
    Page<Course> page = new PageImpl<>(listCourses);
    FeedbackNewDto newFeedback = new FeedbackNewDto();
    CourseDtoShort courseShortDto = new CourseDtoShort();
    FeedbackUpdateDto feedbackUpdateDto = new FeedbackUpdateDto();
    CourseNewDto courseNewDto = new CourseNewDto();
    TopicDto topicDto = new TopicDto();
    CourseUpdateDto courseToUpdate = new CourseUpdateDto();
    String username = "admin";

    @BeforeEach
    public void setup() {
        adminRole.setName("ROLE_ADMIN");
        userRole.setName("ROLE_USER");
        adminRoles.add(adminRole);
        adminRoles.add(userRole);
        userRoles.add(userRole);
        admin.setId(1L);
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setPassword("admin");
        admin.setFirstName("admin");
        admin.setFirstName("admin");
        admin.setRoles(adminRoles);
        user.setId(2L);
        user.setEmail("user@gmail.com");
        user.setUsername("user");
        user.setFirstName("user");
        user.setLastName("user");
        user.setPassword("user");
        user.setFirstName("user");
        user.setFirstName("user");
        user.setRoles(userRoles);
        listUsers.add(user);
        listUsers.add(admin);
        topic.setId(1L);
        topic.setName("Java");
        course.setId(1L);
        course.setName("Course Java");
        course.setTopic(topic);
        course.setUser(admin);
        group.setId(1L);
        group.setName("Group Java");
        group.setCourse(course);
        group.setUsers(listUsers);
        listTopic.add(topic);
        feedback.setId(1L);
        feedback.setRank(5);
        feedback.setDetention("detention");
        feedback.setCourse(course);
        feedback.setUser(user);
        feedback.setDate(LocalDate.now());
        listFeedback.add(feedback);
        courseShortDto.setId(1L);
        courseShortDto.setName(course.getName());
        newFeedback.setDetention("detention");
        newFeedback.setRank(5);
        newFeedback.setCourse(courseShortDto);
        feedbackUpdateDto.setId(1L);
        feedbackUpdateDto.setDetention("some detention");
        feedbackUpdateDto.setRank(1);
        topicDto.setId(1L);
        topicDto.setName("Java");
        courseNewDto.setTopic(topicDto);
        courseNewDto.setName("Course Java");
        courseNewDto.setPrice(0);
        listCourses.add(course);
        courseToUpdate.setId(1L);
        courseToUpdate.setName("Course Java");
        courseToUpdate.setPrice(0);
    }

    @Test
    void save() {
        when(principal.getName()).thenReturn("admin");
        when(userRepository.findByUsername(principal.getName())).thenReturn(admin);
        when(courseRepository.save(any())).thenReturn(course);

        Course newCourse = courseService.save(principal, courseNewDto);

        assertEquals(course, newCourse);
    }

    @Test
    void findById() {
        when(courseRepository.findById(1L)).thenReturn(Optional.ofNullable(course));

        Course findedCourse = courseService.findById(1L);

        assertEquals(course, findedCourse);
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);
        when(courseRepository.findAll(pageable)).thenReturn(page);

        Page<Course> pageCourses = courseService.findAll(pageable);

        assertEquals(page.getContent(), pageCourses.getContent());
    }

    @Test
    void deleteById() {
        when(courseRepository.findById(1L)).thenReturn(Optional.ofNullable(course));

        courseService.deleteById(1L);

        verify(courseRepository, times(1)).deleteById(1L);
    }

    @Test
    void update() {
        when(courseRepository.findById(courseToUpdate.getId())).thenReturn(
            Optional.ofNullable(course));
        when(userRepository.findById(course.getUser().getId())).thenReturn(
            Optional.ofNullable(admin));
        when(principal.getName()).thenReturn("admin");
        when(courseRepository.save(course)).thenReturn(course);

        Course updatedCourse = courseService.update(principal, courseToUpdate);

        assertEquals(updatedCourse, course);
    }

    @Test
    void findAllCoursesByUsername() {
        Pageable pageable = PageRequest.of(0, 10);
        when(courseRepository.findAllCoursesByUsername(pageable, username)).thenReturn(page);

        Page<Course> pageCourses = courseService.findAllCoursesByUsername(pageable, username);

        assertEquals(page.getContent(), pageCourses.getContent());
    }
}