package com.moon.senla.businessservice.model.dto.mapper;

import com.moon.senla.businessservice.model.Course;
import com.moon.senla.businessservice.model.dto.course.CourseDto;
import com.moon.senla.businessservice.model.dto.course.CourseDtoShort;
import com.moon.senla.businessservice.model.dto.course.CourseNewDto;
import com.moon.senla.businessservice.model.dto.course.CourseUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseDto courseToCourseDto(Course course);

    CourseDtoShort courseToCourseDtoShort(Course course);

    CourseNewDto courseToCourseNewDto(Course course);

    Course courseDtoToCourse(CourseDto course);

    Course courseDtoShortToCourse(CourseDtoShort course);

    @Mapping(source = "topicId", target = "topic.id")
    Course courseNewDtoToCourse(CourseNewDto course);

    CourseUpdateDto courseToCourseUpdateDto(Course update);

    Course courseUpdateDtoToCourse(CourseUpdateDto update);
}
