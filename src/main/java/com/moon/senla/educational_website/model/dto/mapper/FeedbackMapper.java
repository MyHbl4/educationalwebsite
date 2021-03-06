package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackDto;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackNewDto;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface FeedbackMapper {

    FeedbackMapper INSTANCE = Mappers.getMapper(FeedbackMapper.class);

    FeedbackDto feedbackToFeedbackDto(Feedback feedback);

    FeedbackNewDto feedbackToFeedbackNewDto(Feedback feedback);

    Feedback feedbackDtoToFeedback(FeedbackDto feedback);

    @Mapping(source = "courseId", target = "course.id")
    Feedback feedbackNewDtoToFeedback(FeedbackNewDto feedback);

    FeedbackUpdateDto feedbackToFeedbackUpdateDto(Feedback feedback);

    Feedback feedbackUpdateDtoToFeedback(FeedbackUpdateDto feedback);
}
