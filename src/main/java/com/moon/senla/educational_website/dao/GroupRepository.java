package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Page<Group> findAll(Pageable pageable);

    Page<Group> findAllByCourse_Id(Pageable pageable, long courseId);
}
