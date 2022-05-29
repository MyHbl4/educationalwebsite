package com.moon.senla.businessservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.moon.senla.businessservice.dao.FeedbackRepository;
import com.moon.senla.businessservice.model.Course;
import com.moon.senla.businessservice.model.Feedback;
import com.moon.senla.businessservice.model.Group;
import com.moon.senla.businessservice.model.Role;
import com.moon.senla.businessservice.model.Topic;
import com.moon.senla.businessservice.model.User;
import com.moon.senla.businessservice.service.CourseService;
import com.moon.senla.businessservice.service.UserService;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceImplTest {

    @Mock
    private Principal principal;

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private CourseService courseService;

    @Mock
    private UserService userService;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    Role adminRole = new Role();
    Role userRole = new Role();
    List<Role> adminRoles = new ArrayList<>();
    List<Role> userRoles = new ArrayList<>();
    User admin = new User();
    User user = new User();
    List<User> listUsers = new ArrayList<>();
    List<Topic> listTopic = new ArrayList<>();
    List<Feedback> listFeedback = new ArrayList<>();
    Group group = new Group();
    Course course = new Course();
    Topic topic = new Topic();
    Feedback feedback = new Feedback();
    Page<Feedback> page = new PageImpl<>(listFeedback);
    Feedback newFeedback = new Feedback();
    Course courseShortDto = new Course();
    Feedback feedbackUpdateDto = new Feedback();

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
    }

    @Test
    void save() {
        when(principal.getName()).thenReturn("user");
        when(userService.findByUsername(principal.getName())).thenReturn(user);
        when(courseService.findById(newFeedback.getCourse().getId())).thenReturn(course);
        when(feedbackRepository.save(
            any())).thenReturn(
            feedback);
        when(feedbackRepository.findAverageRankByCourseId(
            feedback.getCourse()
                .getId())).thenReturn(
            5);
        when(courseService.save(principal, course)).thenReturn(course);

        Feedback savedFeedback = feedbackService.save(principal, newFeedback);

        assertEquals(feedback, savedFeedback);
        assertEquals(5, course.getRating());
        assertEquals("user", savedFeedback.getUser().getUsername());
    }

    @Test
    void findById() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.ofNullable(feedback));

        Feedback testFeedback = feedbackService.findById(1L);

        assertEquals(feedback, testFeedback);
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);
        when(feedbackService.findAll(pageable)).thenReturn(page);

        Page<Feedback> pageFeedback = feedbackService.findAll(pageable);

        assertEquals(page.getContent(), pageFeedback.getContent());
    }

    @Test
    void deleteById() {
        when(feedbackRepository.findById(ArgumentMatchers.anyLong())).thenReturn(
            Optional.ofNullable(feedback));
        when(courseService.findById(feedback.getCourse().getId())).thenReturn(course);
        when(userService.findById(feedback.getUser().getId())).thenReturn(user);
        when(principal.getName()).thenReturn("user");
        when(feedbackRepository.findAverageRankByCourseId(
            course.getId())).thenReturn(5);

        feedbackService.deleteById(principal, 1L);

        verify(feedbackRepository, times(1)).deleteById(1L);
        verify(courseService, times(1)).save(principal, course);
    }

    @Test
    void getAllFeedbackByCourseId() {
        Pageable pageable = PageRequest.of(0, 10);
        when(courseService.findById(1L)).thenReturn(course);
        when(feedbackRepository.findAllByCourseId(pageable, 1L)).thenReturn(page);

        Page<Feedback> pageFeedback = feedbackService.getAllFeedbackByCourseId(pageable, 1L);

        assertEquals(page.getContent(), pageFeedback.getContent());
    }

    @Test
    void update() {
        when(feedbackRepository.findById(feedbackUpdateDto.getId())).thenReturn(
            Optional.of(feedback));
        when(userService.findById(ArgumentMatchers.anyLong())).thenReturn(user);
        when(principal.getName()).thenReturn("user");
        when(courseService.findById(feedback.getCourse().getId())).thenReturn(course);
        when(feedbackRepository.save(
            any())).thenReturn(
            feedback);
        when(feedbackRepository.findAverageRankByCourseId(
            feedback.getCourse()
                .getId())).thenReturn(
            4);
        when(courseService.save(principal, course)).thenReturn(course);

        Feedback updatedFeedback = feedbackService.update(principal, feedbackUpdateDto);

        assertEquals(1, updatedFeedback.getRank());
        assertEquals(4, updatedFeedback.getCourse().getRating());
    }
}