package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackDto;
import com.moon.senla.educational_website.model.dto.mapper.FeedbackMapper;
import com.moon.senla.educational_website.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<FeedbackDto> findAll(@PageableDefault(sort = {"id"})
        Pageable pageable) {
        log.info("find all feedbacks");
        return feedbackService.findAll(pageable)
            .map(FeedbackMapper.INSTANCE::feedbackToFeedbackDto);
    }

    @GetMapping(path = "/{id}")
    public FeedbackDto findById(@PathVariable(name = "id") long id) {
        log.info("find feedback by id {}", id);
        Feedback feedback = feedbackService.findById(id);
        return FeedbackMapper.INSTANCE.feedbackToFeedbackDto(feedback);
    }

    @PostMapping()
    public FeedbackDto save(@RequestBody Feedback feedback) {
        log.info("save feedback {}", feedback);
        Feedback newFeedback = feedbackService.save(feedback);
        return FeedbackMapper.INSTANCE.feedbackToFeedbackDto(newFeedback);
    }

    @PutMapping()
    public FeedbackDto update(@RequestBody Feedback feedbackToUpdate) {
        log.info("update feedback {}", feedbackToUpdate);
        Feedback feedback = feedbackService.save(feedbackToUpdate);
        return FeedbackMapper.INSTANCE.feedbackToFeedbackDto(feedback);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete feedback by id {}", id);
        feedbackService.deleteById(id);
    }
}