package com.moon.senla.educational_website.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.moon.senla.educational_website.dao.GroupRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.Role;
import com.moon.senla.educational_website.model.Status;
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
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private UserServiceImpl userService;

    Role adminRole = new Role();
    Role userRole = new Role();
    List<Role> adminRoles = new ArrayList<>();
    List<Role> userRoles = new ArrayList<>();
    User admin = new User();
    User user = new User();
    List<User> listUsers = new ArrayList<>();
    Group group = new Group();
    Course course = new Course();
    Topic topic = new Topic();
    Page<User> page = new PageImpl<>(listUsers);

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
    }

    @Test
    void findByUsername() {
        when(userRepository.findByUsername("user")).thenReturn(user);

        User testUser = userService.findByUsername("user");

        assertEquals(user, testUser);
    }

    @Test
    void findById() {
        when(userRepository.findById(2L)).thenReturn(Optional.ofNullable(user));

        User testUser = userService.findById(2L);

        assertEquals(user, testUser);
    }

    @Test
    void deleteById() {
        when(userRepository.findById(2L)).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(user)).thenReturn(user);

        User deletedUser = userService.deleteById(user.getId());

        verify(userRepository, times(1)).save(user);
        assertEquals(Status.DELETED, deletedUser.getStatus());
    }

    @Test
    void getAllUsersByGroup_Id() {
        Pageable pageable = PageRequest.of(0, 10);
        when(groupRepository.findById(1L)).thenReturn(Optional.ofNullable(group));
        when(userRepository.getAllUsersByGroup_Id(pageable, 1L)).thenReturn(page);

        Page<User> pageUser = userService.getAllUsersByGroup_Id(pageable, 1L);

        assertEquals(page.getContent(), pageUser.getContent());
    }
}