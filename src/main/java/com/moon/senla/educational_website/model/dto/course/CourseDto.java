package com.moon.senla.educational_website.model.dto.course;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("topic")
    private Topic topic;
    @JsonProperty("user")
    private User user;
}
