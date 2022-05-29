package com.moon.senla.businessservice.dao;

import com.moon.senla.businessservice.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Page<Group> findAll(Pageable pageable);

    Page<Group> findAllByCourseId(Pageable pageable, long courseId);
}
