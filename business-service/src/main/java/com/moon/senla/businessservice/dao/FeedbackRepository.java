package com.moon.senla.businessservice.dao;

import com.moon.senla.businessservice.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    Page<Feedback> findAll(Pageable pageable);

    @Query(
        value =
            "select avg(rank) from feedbacks where course_id = (:course_id)",
        nativeQuery = true)
    int findAverageRankByCourseId(@Param("course_id") long courseId);

    Page<Feedback> findAllByCourseId(Pageable pageable, long courseId);
}
