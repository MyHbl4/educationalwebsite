package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedbackService {

    Feedback save(Feedback feedback);

    Feedback findById(long id);

    Page<Feedback> findAll(Pageable pageable);

    void deleteById(long id);
}

