package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Feedback;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedbackService {

    Feedback findById(String id);

    Page<Feedback> findAll(Pageable pageable);

//    Feedback save(Principal principal, Feedback feedback);
//
//    void deleteById(Principal principal, Long id);
//
//    Feedback update(Principal principal, Feedback feedback);
//
    Page<Feedback> getAllFeedbackByCourseId(Pageable pageable, String courseId);
}

