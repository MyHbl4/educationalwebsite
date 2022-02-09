package com.moon.senla.educational_website.model.dto.feedback;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moon.senla.educational_website.model.dto.course.CourseDtoShort;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackNewDto {
    private String detention;
    private int rank;
    @JsonProperty("course")
    private CourseDtoShort course;
}
