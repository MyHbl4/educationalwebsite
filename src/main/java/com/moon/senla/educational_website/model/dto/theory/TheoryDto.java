package com.moon.senla.educational_website.model.dto.theory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moon.senla.educational_website.model.dto.topic.TopicDto;
import com.moon.senla.educational_website.model.dto.user.UserDtoShort;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheoryDto {
    private String id;
    private String name;
    private LocalDate date;
    private String description;
    @JsonProperty("topic")
    private TopicDto topic;
    @JsonProperty("user")
    private UserDtoShort user;
}
