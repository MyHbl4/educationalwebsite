package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, Long> {

    Page<Feedback> findAll(Pageable pageable);

    //    @Query(
//        value =
//            "select avg(rank) from feedbacks where course_id = (:course_id)",
//        nativeQuery = true)
//    int findAverageRankByCourseId(@Param("course_id") long courseId);

    Page<Feedback> findAllByCourseId(Pageable pageable, String courseId);

    void deleteById(String id);
}
