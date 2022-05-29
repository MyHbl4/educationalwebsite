package com.moon.senla.businessservice.model.dto.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseUpdateDto {
    private Long id;
    private String name;
    private int price;
}
