package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.dto.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseDto courseToCourseDto(Course course);
}
