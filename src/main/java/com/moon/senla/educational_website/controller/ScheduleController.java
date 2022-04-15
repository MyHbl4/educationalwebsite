package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Schedule;
import com.moon.senla.educational_website.model.dto.mapper.ScheduleMapper;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleDto;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleNewDto;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleUpdateDto;
import com.moon.senla.educational_website.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.security.Principal;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @ApiOperation(value = "Get schedule by id")
    @GetMapping(path = "/{id}")
    public ScheduleDto findById(@PathVariable(name = "id") String id) {
        log.info("findById - find schedule by id: {}", id);
        Schedule schedule = scheduleService.findById(id);
        return scheduleMapper.scheduleToScheduleDto(schedule);
    }

    @ApiOperation(value = "Create new schedule")
    @PostMapping()
    public ScheduleDto save(Principal principal, @Valid @RequestBody ScheduleNewDto schedule) {
        log.info("save - save schedule: {}", schedule.getDate());
        Schedule newSchedule = scheduleService.save(principal,
            scheduleMapper.scheduleNewDtoToSchedule(schedule));
        return scheduleMapper.scheduleToScheduleDto(newSchedule);
    }

    @ApiOperation(value = "Update schedule")
    @PutMapping()
    public ScheduleDto update(Principal principal,
        @Valid @RequestBody ScheduleUpdateDto scheduleToUpdate) {
        log.info("update - update schedule by id: {}", scheduleToUpdate.getId());
        Schedule schedule = scheduleService.update(principal,
            scheduleMapper.scheduleUpdateDtoToSchedule(scheduleToUpdate));
        return scheduleMapper.scheduleToScheduleDto(schedule);
    }

    @ApiOperation(value = "Delete schedule")
    @DeleteMapping(path = "/{id}")
    public void delete(Principal principal, @PathVariable(name = "id") String id) {
        log.info("delete - delete schedule by id: {}", id);
        scheduleService.deleteById(principal, id);
    }

    @ApiOperation(value = "Get all schedules")
    @GetMapping()
    public Page<ScheduleDto> findAll(@RequestParam int page) {
        PageRequest pageable = PageRequest.of(page, 5, Direction.ASC, "name");
        log.info("findAll - find all groups");
        return scheduleService.findAll(pageable)
            .map(scheduleMapper::scheduleToScheduleDto);
    }
}