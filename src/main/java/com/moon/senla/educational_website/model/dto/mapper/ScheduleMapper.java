package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.Schedule;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleDateDto;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleDto;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleNewDto;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleShortDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    ScheduleDto scheduleToScheduleDto(Schedule schedule);

    ScheduleNewDto scheduleToScheduleNewDto(Schedule schedule);

    Schedule scheduleDtoToSchedule(ScheduleDto schedule);

    Schedule scheduleNewDtoToSchedule(ScheduleNewDto schedule);

    Schedule scheduleToScheduleDateDto(ScheduleDateDto schedule);

    ScheduleDateDto scheduleDateDtoToSchedule(Schedule schedule);

    ScheduleShortDto scheduleToScheduleShortDto(Schedule schedule);

    Schedule scheduleShortDtoToSchedule(ScheduleShortDto schedule);
}
