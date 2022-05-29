package com.moon.senla.businessservice.model.dto.group;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupNewDto {
    private String name;
    private int capacity;
    private long courseId;
}
