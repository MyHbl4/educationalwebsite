package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackDto;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackNewDto;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackUpdateDto;
import com.moon.senla.educational_website.model.dto.mapper.FeedbackMapper;
import com.moon.senla.educational_website.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.security.Principal;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/feedbacks")
@Slf4j
@Api(tags = "Feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final FeedbackMapper feedbackMapper;

    public FeedbackController(
        FeedbackService feedbackService,
        FeedbackMapper feedbackMapper) {
        this.feedbackService = feedbackService;
        this.feedbackMapper = feedbackMapper;
    }

    @ApiOperation(value = "Get Feedback by id")
    @GetMapping(path = "/{id}")
    public FeedbackDto findById(@PathVariable(name = "id") String id) {
        log.info("findById - find feedback by id: {}", id);
        Feedback feedback = feedbackService.findById(id);
        return feedbackMapper.feedbackToFeedbackDto(feedback);
    }

    @ApiOperation(value = "Get all Feedbacks")
    @GetMapping()
    public Page<FeedbackDto> findAll(@RequestParam int page) {
        PageRequest pageable = PageRequest.of(page, 5, Direction.ASC, "name");
        log.info("findAll - find all groups");
        return feedbackService.findAll(pageable)
            .map(feedbackMapper::feedbackToFeedbackDto);
    }

    @ApiOperation(value = "Create Feedback")
    @PostMapping()
    public FeedbackDto save(Principal principal, @Valid @RequestBody FeedbackNewDto feedback) {
        log.info("save - save feedback by user: {}", principal.getName());
        Feedback newFeedback = feedbackService.save(principal,
            feedbackMapper.feedbackNewDtoToFeedback(feedback));
        return feedbackMapper.feedbackToFeedbackDto(newFeedback);
    }

    @ApiOperation(value = "Update Feedback")
    @PutMapping()
    public FeedbackDto update(Principal principal,
        @Valid @RequestBody FeedbackUpdateDto feedbackToUpdate) {
        log.info("update - update feedback by id: {}", feedbackToUpdate.getId());
        Feedback feedback = feedbackService.update(principal,
            feedbackMapper.feedbackUpdateDtoToFeedback(feedbackToUpdate));
        return feedbackMapper.feedbackToFeedbackDto(feedback);
    }

    @ApiOperation(value = "Delete Feedback")
    @DeleteMapping(path = "/{id}")
    public void delete(Principal principal, @PathVariable(name = "id") String id) {
        log.info("delete - delete feedback by id: {}", id);
        feedbackService.deleteById(principal, id);
    }
}