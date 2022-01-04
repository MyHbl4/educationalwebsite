package com.moon.senla.educational_website.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheoryDto {
    private Long id;
    private String name;
    private LocalDate date;
    private String description;
    @JsonProperty("topic")
    private Topic topic;
    @JsonProperty("user")
    private User user;
}
