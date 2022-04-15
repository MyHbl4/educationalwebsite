package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Schedule;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, Long> {

    Page<Schedule> findAll(Pageable pageable);

    Page<Schedule> findAllByGroupId(Pageable pageable, String groupId);

    Optional<Schedule> findById(String id);

    void deleteById(String id);
}
