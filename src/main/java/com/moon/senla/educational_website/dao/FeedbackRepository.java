package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Feedback;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {

    Page<Feedback> findAll(Pageable pageable);

    Optional<Feedback> findById(String id);

    void deleteById(String id);

    Page<Feedback> findAllByCourseId(Pageable pageable, String courseId);

    List<Feedback> getAllByCourseId(String courseId);
}
