package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Feedback;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    Page<Feedback> findAll(Pageable pageable);

    List<Feedback> findFeedbacksByCourse_Id(long id);
}
