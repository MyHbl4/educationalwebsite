package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
