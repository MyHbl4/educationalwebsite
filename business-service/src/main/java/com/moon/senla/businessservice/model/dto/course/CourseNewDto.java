package com.moon.senla.businessservice.model.dto.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseNewDto {
    private String name;
    private int price;
    private long topicId;
}
