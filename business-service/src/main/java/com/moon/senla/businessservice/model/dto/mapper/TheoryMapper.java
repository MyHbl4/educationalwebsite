package com.moon.senla.businessservice.model.dto.mapper;

import com.moon.senla.businessservice.model.Theory;
import com.moon.senla.businessservice.model.dto.theory.TheoryDto;
import com.moon.senla.businessservice.model.dto.theory.TheoryNewDto;
import com.moon.senla.businessservice.model.dto.theory.TheoryUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TheoryMapper {

    TheoryMapper INSTANCE = Mappers.getMapper(TheoryMapper.class);

    TheoryDto theoryToTheoryDto(Theory theory);

    TheoryNewDto theoryToTheoryNewDto(Theory theory);

    TheoryUpdateDto theoryToTheoryUpdateDto(Theory theoryUpdate);

    Theory theoryDtoToTheory(TheoryDto theory);

    @Mapping(source = "topicId", target = "topic.id")
    Theory theoryNewDtoToTheory(TheoryNewDto theory);

    Theory theoryUpdateDtoToTheory(TheoryUpdateDto theory);
}
