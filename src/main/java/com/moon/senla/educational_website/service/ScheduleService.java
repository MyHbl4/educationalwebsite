package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.Schedule;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {

    Schedule save(Schedule schedule);

    Schedule findById(long id);

    Page<Schedule> findAll(Pageable pageable);

    void deleteById(long id);
}

