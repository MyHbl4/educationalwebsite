package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.dto.CourseDto;
import com.moon.senla.educational_website.model.dto.FeedbackDto;
import com.moon.senla.educational_website.model.dto.GroupDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GroupDto groupToGroupDto(Group group);

    List<GroupDto> listToDtoList(List<Group> groups);
}
