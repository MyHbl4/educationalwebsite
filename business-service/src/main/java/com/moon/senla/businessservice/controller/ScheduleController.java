package com.moon.senla.businessservice.controller;

import com.moon.senla.businessservice.model.Schedule;
import com.moon.senla.businessservice.model.dto.mapper.ScheduleMapper;
import com.moon.senla.businessservice.model.dto.schedule.ScheduleDto;
import com.moon.senla.businessservice.model.dto.schedule.ScheduleNewDto;
import com.moon.senla.businessservice.model.dto.schedule.ScheduleUpdateDto;
import com.moon.senla.businessservice.service.ScheduleService;
import io.swagger.annotations.Api;
import java.security.Principal;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/schedules")
@Slf4j
@Api(tags = "Schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;

    public ScheduleController(ScheduleService scheduleService,
        ScheduleMapper scheduleMapper) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
    }

    @GetMapping(path = "/{id}")
    public ScheduleDto findById(@PathVariable(name = "id") long id) {
        log.info("findById - find schedule by id: {}", id);
        Schedule schedule = scheduleService.findById(id);
        return scheduleMapper.scheduleToScheduleDto(schedule);
    }

    @PostMapping()
    public ScheduleDto save(Principal principal, @Valid @RequestBody ScheduleNewDto schedule) {
        log.info("save - save schedule: {}", schedule.getDate());
        Schedule newSchedule = scheduleService.save(principal,
            scheduleMapper.scheduleNewDtoToSchedule(schedule));
        return scheduleMapper.scheduleToScheduleDto(newSchedule);
    }

    @PutMapping()
    public ScheduleDto update(Principal principal,
        @Valid @RequestBody ScheduleUpdateDto scheduleToUpdate) {
        log.info("update - update schedule by id: {}", scheduleToUpdate.getId());
        Schedule schedule = scheduleService.update(principal,
            scheduleMapper.scheduleUpdateDtoToSchedule(scheduleToUpdate));
        return scheduleMapper.scheduleToScheduleDto(schedule);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(Principal principal, @PathVariable(name = "id") long id) {
        log.info("delete - delete schedule by id: {}", id);
        scheduleService.deleteById(principal, id);
    }
}