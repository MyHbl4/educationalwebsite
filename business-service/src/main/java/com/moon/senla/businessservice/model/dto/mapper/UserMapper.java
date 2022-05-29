package com.moon.senla.businessservice.model.dto.mapper;

import com.moon.senla.businessservice.model.User;
import com.moon.senla.businessservice.model.dto.user.UserDto;
import com.moon.senla.businessservice.model.dto.user.UserDtoShort;
import com.moon.senla.businessservice.model.dto.user.UserDtoUpdate;
import com.moon.senla.businessservice.model.dto.user.UserGroupDto;
import com.moon.senla.businessservice.model.dto.user.UserNewDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    UserDtoShort userToUserDtoShort(User user);

    UserNewDto userToUserNewDto(User user);

    UserDtoUpdate userToUserDtoUpdate(User user);

    UserGroupDto userToUserGroupDto(User user);

    User userDtoToUser(UserDto userDto);

    User userDtoShortToUser(UserDtoShort userDtoShort);

    User userNewDtoToUser(UserNewDto userNewDto);

    User userDtoUpdateToUser(UserDtoUpdate userDtoUpdate);

    User userGroupDtoToUser(UserGroupDto userToUserGroupDto);
}
