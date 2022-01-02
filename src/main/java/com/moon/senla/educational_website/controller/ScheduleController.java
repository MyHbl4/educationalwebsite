package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Schedule;
import com.moon.senla.educational_website.service.ScheduleService;
import java.util.List;
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
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping()
    public List<Schedule> findAll() {
        log.info("find all schedules");
        return scheduleService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Schedule findById(@PathVariable(name = "id") long id) {
        log.info("find schedule by id {}", id);
        return scheduleService.findById(id);
    }

    @PostMapping()
    public Schedule save(@RequestBody Schedule schedule) {
        log.info("save schedule {}", schedule);
        return scheduleService.save(schedule);
    }

    @PutMapping()
    public Schedule update(@RequestBody Schedule scheduleToUpdate) {
        log.info("update schedule {}", scheduleToUpdate);
        return scheduleService.save(scheduleToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete schedule by id {}", id);
        scheduleService.deleteById(id);
    }
}