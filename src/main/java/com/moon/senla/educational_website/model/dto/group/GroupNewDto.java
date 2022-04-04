package com.moon.senla.educational_website.model.dto.group;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupNewDto {
    private String name;
    private int capacity;
    private long courseId;
}
