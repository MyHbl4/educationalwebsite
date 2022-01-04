package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.Schedule;
import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Page<Schedule> findAll(Pageable pageable);

}
