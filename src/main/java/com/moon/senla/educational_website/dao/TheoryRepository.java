package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Theory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheoryRepository extends JpaRepository<Theory, Long> {

    Page<Theory> findAll(Pageable pageable);
}
