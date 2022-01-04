package com.moon.senla.educational_website.model.dto.course;

import com.moon.senla.educational_website.model.Course;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoursePageDto {
    private List<Course> courses;
    private int currentPage;
    private int totalPages;
}
