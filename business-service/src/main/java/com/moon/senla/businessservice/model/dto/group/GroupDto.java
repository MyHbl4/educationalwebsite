package com.moon.senla.businessservice.model.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moon.senla.businessservice.model.dto.course.CourseDtoShort;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDto {
    private Long id;
    private String name;
    @JsonProperty("course")
    private CourseDtoShort course;
    private int capacity;
    private int available;
}
