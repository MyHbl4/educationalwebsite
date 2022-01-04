package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.CourseDto;
import com.moon.senla.educational_website.model.dto.UserDto;
import com.moon.senla.educational_website.model.dto.mapper.CourseMapper;
import com.moon.senla.educational_website.model.dto.mapper.UserMapper;
import com.moon.senla.educational_website.service.UserService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserDto> findAll(@PageableDefault(sort = {"username"}, size = 5)
        Pageable pageable) {
        log.info("find all users");
        Page<User> pageUsers = userService.findAll(pageable);
        List<User> users = pageUsers.getContent();
        return UserMapper.INSTANCE.listToDtoList(users);
    }

    @GetMapping(path = "/{id}")
    public UserDto findById(@PathVariable(name = "id") long id) {
        log.info("find user by id {}", id);
        User user = userService.findById(id);
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @PostMapping()
    public UserDto save(@RequestBody User user) {
        log.info("save user {}", user);
        User newUser = userService.save(user);
        return UserMapper.INSTANCE.userToUserDto(newUser);

    }

    @PutMapping()
    public UserDto update(@RequestBody User userToUpdate) {
        log.info("update user {}", userToUpdate);
        User user = userService.save(userToUpdate);
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete user by id {}", id);
        userService.deleteById(id);
    }

    @GetMapping("/{id}/courses")
    public List<CourseDto> findAllCoursesByUserId(@PathVariable(name = "id") long id) {
        log.info("find all courses by user id {}", id);
        List<Course> list = userService.findAllCoursesByUserId(id);
        return CourseMapper.INSTANCE.listToDtoList(list);
    }
}