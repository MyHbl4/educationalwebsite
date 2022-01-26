package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.user.AuthenticationRequestDto;
import com.moon.senla.educational_website.model.dto.user.UserDto;
import com.moon.senla.educational_website.security.jwt.JwtTokenProvider;
import com.moon.senla.educational_website.service.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
        JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException(
                    "User with username: " + username + " not found!!!!!!!!!!!!!!!");
            }

            String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password!!!!!!!!!!");
        }
    }

//    @PostMapping("/register")
//    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid User user, @ModelAttribute("user") User usr){
//        User newUser = new User();
//        newUser.setEmail(user.getEmail());
//        newUser.setUsername(user.getUsername());
//        newUser.setFirstName(user.getFirstName());
//        newUser.setLastName(user.getLastName());
//        newUser.setPassword(user.getPassword());
//        userService.register(newUser);
//        UserDto result = UserDto.fromUser(userService.findByUsername(newUser.getUsername()));
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
}