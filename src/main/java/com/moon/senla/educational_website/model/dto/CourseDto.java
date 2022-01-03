package com.moon.senla.educational_website.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {
    private Long id;
    private String name;
    private int price;
    private int ranking;
}
