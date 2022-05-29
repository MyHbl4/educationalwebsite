package com.moon.senla.businessservice.controller;

import com.moon.senla.businessservice.model.User;
import com.moon.senla.businessservice.model.dto.mapper.UserMapper;
import com.moon.senla.businessservice.model.dto.user.AuthenticationRequestDto;
import com.moon.senla.businessservice.model.dto.user.UserDto;
import com.moon.senla.businessservice.model.dto.user.UserNewDto;
import com.moon.senla.businessservice.service.AuthenticationService;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@Slf4j
@Api(tags = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @Autowired
    public AuthenticationController(
        AuthenticationService authenticationService,
        UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequestDto requestDto) {
        log.info("login - login user with username: {}", requestDto.getUsername());
        return ResponseEntity.ok(authenticationService.login(requestDto));
    }

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody @Valid UserNewDto user) {
        log.info("registerUser - register user with username: {}", user.getUsername());
        User newUser = authenticationService.register(userMapper.userNewDtoToUser(user));
        return userMapper.userToUserDto(newUser);
    }
}