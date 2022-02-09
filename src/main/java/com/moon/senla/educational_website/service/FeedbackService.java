package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackNewDto;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackUpdateDto;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedbackService {

    Feedback save(Principal principal, FeedbackNewDto feedback);

    Feedback findById(long id);

    Page<Feedback> findAll(Pageable pageable);

    void deleteById(Principal principal, long id);

    Page<Feedback> getAllFeedbackByCourseId(Pageable pageable, long courseId);

    Feedback update(Principal principal, FeedbackUpdateDto feedback);
}

