package com.moon.senla.educational_website.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/say")
public class SomeController {

    @GetMapping("/hello")
    public String say(){
        return "Hello without api";
    }

    @GetMapping("/helloo")
    public String sayHello(){
        return "Hello with api";
    }
}
