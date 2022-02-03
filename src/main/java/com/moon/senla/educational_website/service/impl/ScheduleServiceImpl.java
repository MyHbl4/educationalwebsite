package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.ScheduleRepository;
import com.moon.senla.educational_website.model.Schedule;
import com.moon.senla.educational_website.service.ScheduleService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule findById(long id) {
        Schedule schedule = null;
        Optional<Schedule> option = scheduleRepository.findById(id);
        if (option.isPresent()) {
            schedule = option.get();
        }
        return schedule;
    }

    @Override
    public Page<Schedule> findAll(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    @Override
    public void deleteById(long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public Page<Schedule> findAllByGroup_Id(Pageable pageable, long groupId) {
        return scheduleRepository.findAllByGroup_Id(pageable, groupId);
    }
}
