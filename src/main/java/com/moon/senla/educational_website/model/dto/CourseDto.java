package com.moon.senla.educational_website.model.dto;

import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {
    private Long id;
    private String name;
    private int price;
    private int ranking;
    private Topic topic;
    private User user;
}
