package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Schedule;
import java.security.Principal;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {

    Schedule save(Principal principal, Schedule schedule);

    Schedule findById(String id);

    Page<Schedule> findAll(Pageable pageable);

    void deleteById(Principal principal, String id);

    Page<Schedule> findAllByGroupId(Pageable pageable, String groupId);

    Schedule update(Principal principal, Schedule schedule);
}

