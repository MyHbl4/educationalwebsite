package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.UserService;
import java.util.List;
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
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}")
    public User findById(@PathVariable(name = "id") long id) {
        return userService.findById(id);
    }

    @PostMapping()
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping()
    public User update(@RequestBody User userToUpdate) {
        return userService.save(userToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        userService.deleteById(id);
    }
}