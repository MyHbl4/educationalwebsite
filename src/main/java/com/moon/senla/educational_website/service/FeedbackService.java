package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Feedback;
import java.util.List;

public interface FeedbackService {

    Feedback save(Feedback feedback);

    Feedback findById(long id);

    List<Feedback> findAll();

    void deleteById(long id);
}

