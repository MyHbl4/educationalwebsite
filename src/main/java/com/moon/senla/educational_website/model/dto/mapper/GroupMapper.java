package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.dto.group.GroupDto;
import com.moon.senla.educational_website.model.dto.group.GroupNewDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GroupDto groupToGroupDto(Group group);

    GroupNewDto groupToGroupNewDto(Group group);

    Group groupNewDtoToGroup(GroupNewDto group);

    Group groupDtoToGroup(GroupDto group);
}
