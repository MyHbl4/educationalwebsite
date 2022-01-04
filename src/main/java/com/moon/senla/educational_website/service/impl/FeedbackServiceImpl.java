package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.FeedbackRepository;
import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.service.FeedbackService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private final FeedbackRepository feedbackRepository;

    @Override
    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback findById(long id) {
        Feedback feedback = null;
        Optional<Feedback> option = feedbackRepository.findById(id);
        if (option.isPresent()) {
            feedback = option.get();
        }
        return feedback;
    }

    @Override
    public Page<Feedback> findAll(Pageable pageable) {
        return feedbackRepository.findAll(pageable);
    }

    @Override
    public void deleteById(long id) {
        feedbackRepository.deleteById(id);
    }
}
