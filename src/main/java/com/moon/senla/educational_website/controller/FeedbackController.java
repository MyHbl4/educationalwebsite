package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.service.FeedbackService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/feedbacks")
@Slf4j
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping()
    public List<Feedback> findAll() {
        log.info("find all feedbacks ");
        return feedbackService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Feedback findById(@PathVariable(name = "id") long id) {
        log.info("find feedback by id {}", id);
        return feedbackService.findById(id);
    }

    @PostMapping()
    public Feedback save(@RequestBody Feedback feedback) {
        log.info("save feedback {}", feedback);
        return feedbackService.save(feedback);
    }

    @PutMapping()
    public Feedback update(@RequestBody Feedback feedbackToUpdate) {
        log.info("update feedback {}", feedbackToUpdate);
        return feedbackService.save(feedbackToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete feedback by id {}", id);
        feedbackService.deleteById(id);
    }
}