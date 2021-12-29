package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.Schedule;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public interface ScheduleService {

    Schedule save(Schedule schedule);

    Schedule findById(long id);

    List<Schedule> findAll();

    void deleteById(long id);
}

