package com.moon.senla.educational_website.model.dto.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseUpdateDto {
    private String id;
    private String name;
    private int price;
}
