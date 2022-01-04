package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.model.Schedule;
import com.moon.senla.educational_website.model.dto.FeedbackDto;
import com.moon.senla.educational_website.model.dto.ScheduleDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    ScheduleDto scheduleToScheduleDto(Schedule schedule);

    List<ScheduleDto> listToDtoList(List<Schedule> schedule);

}
