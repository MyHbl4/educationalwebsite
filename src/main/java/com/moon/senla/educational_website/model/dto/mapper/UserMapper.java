package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.user.UserDto;
import com.moon.senla.educational_website.model.dto.user.UserDtoShort;
import com.moon.senla.educational_website.model.dto.user.UserDtoUpdate;
import com.moon.senla.educational_website.model.dto.user.UserNewDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    UserDtoShort userToUserDtoShort(User user);

    UserNewDto userToUserNewDto(User user);

    UserDtoUpdate userToUserDtoUpdate(User user);

    User userDtoToUser(UserDto userDto);

    User userDtoShortToUser(UserDtoShort userDtoShort);

    User userNewDtoToUser(UserNewDto userNewDto);

    User userDtoUpdateToUser(UserDtoUpdate userDtoUpdate);
}
