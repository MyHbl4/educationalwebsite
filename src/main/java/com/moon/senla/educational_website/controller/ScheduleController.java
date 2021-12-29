package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Schedule;
import com.moon.senla.educational_website.service.ScheduleService;
import java.util.List;
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
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping()
    public List<Schedule> findAll() {
        return scheduleService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Schedule findById(@PathVariable(name = "id") long id) {
        return scheduleService.findById(id);
    }

    @PostMapping()
    public Schedule save(@RequestBody Schedule schedule) {
        return scheduleService.save(schedule);
    }

    @PutMapping()
    public Schedule update(@RequestBody Schedule scheduleToUpdate) {
        return scheduleService.save(scheduleToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        scheduleService.deleteById(id);
    }
}