package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Schedule;
import com.moon.senla.educational_website.model.dto.schedule.SchedulePageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {

    Schedule save(Schedule schedule);

    Schedule findById(long id);

    Page<Schedule> findAll(Pageable pageable);

    SchedulePageDto findAllPageable(Pageable pageable);

    void deleteById(long id);
}

