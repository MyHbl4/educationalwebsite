package com.moon.senla.educational_website.model.dto.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moon.senla.educational_website.model.dto.course.CourseDtoShort;
import com.moon.senla.educational_website.model.dto.user.UserDtoShort;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackNewDto {
    private Long id;
    private LocalDate date;
    private String detention;
    private int rank;
    @JsonProperty("course")
    private CourseDtoShort course;
    @JsonProperty("user")
    private UserDtoShort user;
}
