package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.ScheduleRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Schedule;
import com.moon.senla.educational_website.service.ScheduleService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(
        ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule save(Schedule schedule) {
        try {
            return scheduleRepository.save(schedule);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, schedule could not be saved");
        }
    }

    @Override
    public Schedule findById(long id) {
        Schedule schedule = null;
        Optional<Schedule> option = scheduleRepository.findById(id);
        if (option.isPresent()) {
            schedule = option.get();
        }
        if (schedule == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Schedule Not Found");
        }
        return schedule;
    }

    @Override
    public Page<Schedule> findAll(Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);
        if (schedules.getContent().isEmpty()) {
            throw new CustomException(HttpStatus.NO_CONTENT,
                "Request has been successfully processed and the response is  blank. Schedules Not Found");
        }
        return schedules;
    }

    @Override
    public void deleteById(long id) {
        try {
            scheduleRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Schedule Not Found");
        }
    }

    @Override
    public Page<Schedule> findAllByGroup_Id(Pageable pageable, long groupId) {
        try {
            return scheduleRepository.findAllByGroup_Id(pageable, groupId);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, schedules cannot be found");
        }
    }
}
