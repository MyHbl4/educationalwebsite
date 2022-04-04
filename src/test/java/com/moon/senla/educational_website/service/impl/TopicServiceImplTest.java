package com.moon.senla.educational_website.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.moon.senla.educational_website.dao.TopicRepository;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.Role;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
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
class TopicServiceImplTest {

    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private TopicServiceImpl topicService;

    Role adminRole = new Role();
    Role userRole = new Role();
    List<Role> adminRoles = new ArrayList<>();
    List<Role> userRoles = new ArrayList<>();
    User admin = new User();
    User user = new User();
    List<User> listUsers = new ArrayList<>();
    List<Topic> listTopic = new ArrayList<>();
    Group group = new Group();
    Course course = new Course();
    Topic topic = new Topic();
    Page<Topic> page = new PageImpl<>(listTopic);

    @BeforeEach
    public void setup() {
        adminRole.setName("admin");
        userRole.setName("user");
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
    }

    @Test
    void save() {
        when(topicRepository.save(topic)).thenReturn(topic);

        Topic testTopic = topicService.save(topic);

        assertEquals(topic, testTopic);
    }

    @Test
    void findById() {
        when(topicRepository.findById(1L)).thenReturn(Optional.ofNullable(topic));

        Topic testTopic = topicService.findById(1L);

        assertEquals(topic, testTopic);
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);
        when(topicRepository.findAll(pageable)).thenReturn(page);
        Page<Topic> pageTopic = topicService.findAll(pageable);
        assertEquals(page.getContent(), pageTopic.getContent());
    }

    @Test
    void deleteById() {
        when(topicRepository.findById(1L)).thenReturn(Optional.ofNullable(topic));

        topicService.deleteById(1L);

        verify(topicRepository, times(1)).deleteById(1L);
    }

    @Test
    void update() {
        Topic topicToUpdate = topic;
        topicToUpdate.setName("C++");
        when(topicRepository.findById(topicToUpdate.getId())).thenReturn(
            Optional.ofNullable(topic));
        when(topicRepository.save(topicToUpdate)).thenReturn(topicToUpdate);

        Topic updatedTopic = topicService.update(topicToUpdate);

        assertEquals("C++", updatedTopic.getName());
    }
}