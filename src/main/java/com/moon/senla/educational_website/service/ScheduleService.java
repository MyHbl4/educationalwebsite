package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {

    Schedule save(Schedule schedule);

    Schedule findById(long id);

    Page<Schedule> findAll(Pageable pageable);

    void deleteById(long id);

    Page<Schedule> findAllByGroup_Id(Pageable pageable, long groupId);
}

