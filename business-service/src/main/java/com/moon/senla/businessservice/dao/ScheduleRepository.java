package com.moon.senla.businessservice.dao;

import com.moon.senla.businessservice.model.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Page<Schedule> findAll(Pageable pageable);

    Page<Schedule> findAllByGroupId(Pageable pageable, long groupId);
}
