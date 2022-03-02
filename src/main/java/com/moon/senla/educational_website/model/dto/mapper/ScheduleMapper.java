package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.Schedule;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleDateDto;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleDto;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleNewDto;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ScheduleMapper {

    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    ScheduleDto scheduleToScheduleDto(Schedule schedule);

    ScheduleNewDto scheduleToScheduleNewDto(Schedule schedule);

    ScheduleDateDto scheduleDateDtoToSchedule(Schedule schedule);

    ScheduleUpdateDto scheduleToScheduleUpdateDto(Schedule schedule);

    Schedule scheduleDtoToSchedule(ScheduleDto schedule);

    Schedule scheduleNewDtoToSchedule(ScheduleNewDto schedule);

    Schedule scheduleToScheduleDateDto(ScheduleDateDto schedule);

    Schedule scheduleUpdateDtoToSchedule(ScheduleUpdateDto schedule);
}
