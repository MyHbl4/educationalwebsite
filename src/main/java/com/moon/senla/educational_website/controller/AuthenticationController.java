package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.mapper.UserMapper;
import com.moon.senla.educational_website.model.dto.user.AuthenticationRequestDto;
import com.moon.senla.educational_website.model.dto.user.UserDto;
import com.moon.senla.educational_website.service.AuthenticationService;
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

    @Autowired
    public AuthenticationController(
        AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        return ResponseEntity.ok(authenticationService.login(requestDto));
    }

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody @Valid User user) {
        User newUser = authenticationService.register(user);
        return UserMapper.INSTANCE.userToUserDto(newUser);
    }
}