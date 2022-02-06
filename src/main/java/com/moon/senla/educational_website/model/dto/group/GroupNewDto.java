package com.moon.senla.educational_website.model.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moon.senla.educational_website.model.dto.course.CourseDtoShort;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupNewDto {
    private String name;
    @JsonProperty("course")
    private CourseDtoShort course;
}