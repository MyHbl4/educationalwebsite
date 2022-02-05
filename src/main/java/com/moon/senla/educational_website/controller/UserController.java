package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.course.CourseDto;
import com.moon.senla.educational_website.model.dto.mapper.CourseMapper;
import com.moon.senla.educational_website.model.dto.mapper.UserMapper;
import com.moon.senla.educational_website.model.dto.user.UserDto;
import com.moon.senla.educational_website.model.dto.user.UserDtoUpdate;
import com.moon.senla.educational_website.model.dto.user.UserNewDto;
import com.moon.senla.educational_website.service.AuthenticationService;
import com.moon.senla.educational_website.service.UserService;
import com.moon.senla.educational_website.service.impl.SearchFilterServiceImpl;
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
@RequestMapping(value = "/api/users")
@Slf4j
@Api(tags = "Users")
public class UserController {

    private final UserService userService;
    private final SearchFilterServiceImpl searchFilterService;
    private final AuthenticationService authenticationService;

    public UserController(UserService userService,
        SearchFilterServiceImpl searchFilterService,
        AuthenticationService authenticationService) {
        this.userService = userService;
        this.searchFilterService = searchFilterService;
        this.authenticationService = authenticationService;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Page<UserDto> findAll(
        @PageableDefault(sort = {"id"}) Pageable pageable) {
        log.info("find all users");
        return userService.findAll(pageable)
            .map(UserMapper.INSTANCE::userToUserDto);
    }

    @GetMapping(path = "/search")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Page<UserDto> findAllUsersByParam(
        @PageableDefault(sort = {"id"}) Pageable pageable,
        @RequestParam(value = "firstName", required = false) String firstName,
        @RequestParam(value = "LastName", required = false) String lastName) {
        return searchFilterService.findAllUsersByParam(pageable, firstName, lastName)
            .map(UserMapper.INSTANCE::userToUserDto);
    }

    @GetMapping(path = "/{id}")
    public UserDto findById(@PathVariable(name = "id") long id) {
        log.info("find user by id {}", id);
        User user = userService.findById(id);
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserDto newUser(@RequestBody UserNewDto user) {
        log.info("save user {}", user);
        User newUser = authenticationService.register(user);
        return UserMapper.INSTANCE.userToUserDto(newUser);
    }

    @PutMapping()
    @PreAuthorize("#userToUpdate.username == authentication.name")
    public UserDto update(@RequestBody UserDtoUpdate userToUpdate) {
        log.info("update user {}", userToUpdate);
        User user = authenticationService.update(userToUpdate);
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete user by id {}", id);
        userService.deleteById(id);
    }

    @GetMapping(path = "/{id}/courses")
    public Page<CourseDto> findAllCoursesByUserId(
        @PathVariable(name = "id") long id,
        Pageable pageable) {
        log.info("find courses where author is user and him id {}", id);
        return userService.findAllCoursesByUserId(pageable, id)
            .map(CourseMapper.INSTANCE::courseToCourseDto);
    }
}