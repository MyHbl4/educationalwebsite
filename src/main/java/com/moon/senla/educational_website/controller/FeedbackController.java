package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.service.FeedbackService;
import java.util.List;
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
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping()
    public List<Feedback> findAll() {
        return feedbackService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Feedback findById(@PathVariable(name = "id") long id) {
        return feedbackService.findById(id);
    }

    @PostMapping()
    public Feedback save(@RequestBody Feedback feedback) {
        return feedbackService.save(feedback);
    }

    @PutMapping()
    public Feedback update(@RequestBody Feedback feedbackToUpdate) {
        return feedbackService.save(feedbackToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        feedbackService.deleteById(id);
    }
}