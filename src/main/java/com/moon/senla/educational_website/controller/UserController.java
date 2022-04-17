package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.mapper.UserMapper;
import com.moon.senla.educational_website.model.dto.user.UserDto;
import com.moon.senla.educational_website.model.dto.user.UserDtoUpdate;
import com.moon.senla.educational_website.model.dto.user.UserNewDto;
import com.moon.senla.educational_website.service.AuthenticationService;
import com.moon.senla.educational_website.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.security.Principal;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public UserController(UserService userService,
        AuthenticationService authenticationService,
        UserMapper userMapper) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }

    @ApiOperation(value = "Get user by id")
    @GetMapping(path = "/{id}")
    public UserDto findById(@PathVariable(name = "id") String id) {
        log.info("findById - find user by id: {}", id);
        User user = userService.findById(id);
        return userMapper.userToUserDto(user);
    }

    @ApiOperation(value = "Save new user, only for admin")
    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserDto save(@Valid @RequestBody UserNewDto user) {
        log.info("save - save user with username: {}", user.getUsername());
        User newUser = authenticationService.register(userMapper.userNewDtoToUser(user));
        return userMapper.userToUserDto(newUser);
    }

    @ApiOperation(value = "Update user data")
    @PutMapping()
    public UserDto update(Principal principal, @Valid @RequestBody UserDtoUpdate userToUpdate) {
        log.info("update - update user with username: {}", principal.getName());
        User user = authenticationService.update(principal,
            userMapper.userDtoUpdateToUser(userToUpdate));
        return userMapper.userToUserDto(user);
    }

    @ApiOperation(value = "Delete user by id, only for admin")
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserDto delete(@PathVariable(name = "id") String id) {
        log.info("delete - delete user by id: {}", id);
        return userMapper.userToUserDto(userService.deleteById(id));
    }

    @ApiOperation(value = "Show data about user")
    @GetMapping(path = "/my-page")
    public UserDto showMyPage(Principal principal) {
        log.info("showMyPage - show {} page", principal.getName());
        User user = userService.findByUsername(principal.getName());
        return userMapper.userToUserDto(user);
    }

    @ApiOperation(value = "Get all users")
    @GetMapping()
    public Page<UserDto> findAll(@RequestParam int page) {
        PageRequest pageable = PageRequest.of(page, 5, Direction.ASC, "username");
        log.info("findAll - find all users");
        return userService.findAll(pageable)
            .map(userMapper::userToUserDto);
    }

    @ApiOperation(value = "Get all users by first name and last name")
    @GetMapping(path = "/search-by-param")
    public Page<UserDto> findAllUsersByParam(
        @RequestParam(value = "FirstName", required = false) String firstName,
        @RequestParam(value = "LastName", required = false) String lastName,
        @RequestParam int page) {
        PageRequest pageable = PageRequest.of(page, 5, Direction.ASC, "firstName");
        log.info("findByLastName - find all users by param");
        return userService.findAllUsersByParam(pageable, firstName, lastName)
            .map(userMapper::userToUserDto);
    }
}