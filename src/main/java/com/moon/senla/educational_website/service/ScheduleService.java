package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Schedule;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {

    Schedule save(Principal principal, Schedule schedule);

    Schedule findById(long id);

    Page<Schedule> findAll(Pageable pageable);

    void deleteById(Principal principal, long id);

    Page<Schedule> findAllByGroupId(Pageable pageable, long groupId);

    Schedule update(Principal principal, Schedule schedule);
}

