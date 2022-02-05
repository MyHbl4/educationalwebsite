package com.moon.senla.educational_website.model.dto.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moon.senla.educational_website.model.dto.topic.TopicDto;
import com.moon.senla.educational_website.model.dto.user.UserDtoShort;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseNewDto {
    private String name;
    private int price;
    @JsonProperty("topic")
    private TopicDto topic;
    @JsonProperty("user")
    private UserDtoShort user;
}
