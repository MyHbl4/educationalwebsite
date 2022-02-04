package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.user.UserDto;
import com.moon.senla.educational_website.model.dto.user.UserDtoShort;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    UserDtoShort userToUserDtoShort(User user);
}
