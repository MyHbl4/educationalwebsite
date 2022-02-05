package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackDto;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackNewDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    FeedbackMapper INSTANCE = Mappers.getMapper(FeedbackMapper.class);

    FeedbackDto feedbackToFeedbackDto(Feedback feedback);

    FeedbackNewDto feedbackToFeedbackNewDto(Feedback feedback);

    Feedback feedbackDtoToFeedback(FeedbackDto feedback);

    Feedback feedbackNewDtoToFeedback(FeedbackNewDto feedback);
}
