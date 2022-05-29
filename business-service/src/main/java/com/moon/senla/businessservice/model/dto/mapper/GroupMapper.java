package com.moon.senla.businessservice.model.dto.mapper;

import com.moon.senla.businessservice.model.Group;
import com.moon.senla.businessservice.model.dto.group.GroupDto;
import com.moon.senla.businessservice.model.dto.group.GroupNewDto;
import com.moon.senla.businessservice.model.dto.group.GroupShortDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GroupDto groupToGroupDto(Group group);

    GroupNewDto groupToGroupNewDto(Group group);

    GroupShortDto groupToGroupShortDto(Group group);

    @Mapping(source = "courseId", target = "course.id")
    Group groupNewDtoToGroup(GroupNewDto group);

    Group groupDtoToGroup(GroupDto group);

    Group groupShortDtoToGroup(GroupShortDto group);
}
