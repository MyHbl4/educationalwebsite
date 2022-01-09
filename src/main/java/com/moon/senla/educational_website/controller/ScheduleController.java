package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Schedule;
import com.moon.senla.educational_website.model.dto.mapper.ScheduleMapper;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleDto;
import com.moon.senla.educational_website.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping()
    public Page<ScheduleDto> findAll(@PageableDefault(sort = {"id"})
        Pageable pageable) {
        log.info("find all schedules");
        return scheduleService.findAll(pageable)
            .map(ScheduleMapper.INSTANCE::scheduleToScheduleDto);
    }

    @GetMapping(path = "/{id}")
    public ScheduleDto findById(@PathVariable(name = "id") long id) {
        log.info("find schedule by id {}", id);
        Schedule schedule = scheduleService.findById(id);
        return ScheduleMapper.INSTANCE.scheduleToScheduleDto(schedule);
    }

    @PostMapping()
    public ScheduleDto save(@RequestBody Schedule schedule) {
        log.info("save schedule {}", schedule);
        Schedule newSchedule = scheduleService.save(schedule);
        return ScheduleMapper.INSTANCE.scheduleToScheduleDto(newSchedule);
    }

    @PutMapping()
    public ScheduleDto update(@RequestBody Schedule scheduleToUpdate) {
        log.info("update schedule {}", scheduleToUpdate);
        Schedule schedule = scheduleService.save(scheduleToUpdate);
        return ScheduleMapper.INSTANCE.scheduleToScheduleDto(schedule);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete schedule by id {}", id);
        scheduleService.deleteById(id);
    }
}