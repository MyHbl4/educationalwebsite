package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.dto.theory.TheoryDto;
import com.moon.senla.educational_website.model.dto.theory.TheoryNewDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TheoryMapper {
    TheoryMapper INSTANCE = Mappers.getMapper(TheoryMapper.class);

    TheoryDto theoryToTheoryDto(Theory theory);

    TheoryNewDto theoryToTheoryNewDto(Theory theory);

    Theory theoryDtoToTheory(TheoryDto theory);

    Theory theoryNewDtoToTheory(TheoryNewDto theory);
}
