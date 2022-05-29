package com.moon.senla.businessservice.service;

import com.moon.senla.businessservice.model.Feedback;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedbackService {

    Feedback save(Principal principal, Feedback feedback);

    Feedback findById(Long id);

    Page<Feedback> findAll(Pageable pageable);

    void deleteById(Principal principal, Long id);

    Page<Feedback> getAllFeedbackByCourseId(Pageable pageable, Long courseId);

    Feedback update(Principal principal, Feedback feedback);
}

