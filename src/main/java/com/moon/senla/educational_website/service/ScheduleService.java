package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Schedule;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleNewDto;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleUpdateDto;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {

    Schedule save(Principal principal, ScheduleNewDto schedule);

    Schedule findById(long id);

    Page<Schedule> findAll(Pageable pageable);

    void deleteById(Principal principal, long id);

    Page<Schedule> findAllByGroup_Id(Pageable pageable, long groupId);

    Schedule update(Principal principal, ScheduleUpdateDto schedule);
}

