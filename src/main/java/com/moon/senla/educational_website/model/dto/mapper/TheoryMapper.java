package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.dto.theory.TheoryDto;
import com.moon.senla.educational_website.model.dto.theory.TheoryNewDto;
import com.moon.senla.educational_website.model.dto.theory.TheoryUpdateDto;
import org.mapstruct.Mapper;
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

    Theory theoryNewDtoToTheory(TheoryNewDto theory);

    Theory theoryUpdateDtoToTheory(TheoryUpdateDto theory);
}
