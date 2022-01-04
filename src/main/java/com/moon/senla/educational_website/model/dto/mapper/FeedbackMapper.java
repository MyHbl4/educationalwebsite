package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.model.dto.CourseDto;
import com.moon.senla.educational_website.model.dto.FeedbackDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    FeedbackMapper INSTANCE = Mappers.getMapper(FeedbackMapper.class);

    FeedbackDto feedbackToFeedbackDto(Feedback feedback);

    List<FeedbackDto> listToDtoList(List<Feedback> feedbacks);
}
