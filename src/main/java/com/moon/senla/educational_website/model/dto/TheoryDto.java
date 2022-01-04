package com.moon.senla.educational_website.model.dto;

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
    private Topic topic;
    private User user;
}
