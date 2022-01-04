package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.dto.FeedbackDto;
import com.moon.senla.educational_website.model.dto.TheoryDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TheoryMapper {
    TheoryMapper INSTANCE = Mappers.getMapper(TheoryMapper.class);

    TheoryDto theoryToTheoryDto(Theory theory);

    List<TheoryDto> listToDtoList(List<Theory> theories);
}
