package com.moon.senla.educational_website.service.impl;


import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.educational_website.utils.StringConstants.FEEDBACK_NF;

import com.moon.senla.educational_website.dao.FeedbackRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.CourseService;
import com.moon.senla.educational_website.service.FeedbackService;
import com.moon.senla.educational_website.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final CourseService courseService;
    private final UserService userService;

    @Autowired
    public FeedbackServiceImpl(
        FeedbackRepository feedbackRepository,
        CourseService courseService,
        UserService userService) {
        this.feedbackRepository = feedbackRepository;
        this.courseService = courseService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Feedback save(Principal principal, Feedback feedback) {
        User user = userService.findByUsername(principal.getName());
        Course course = courseService.findById(feedback.getCourse().getId());
        feedback.setUser(user);
        feedback.setCourse(course);
        try {
            Feedback feed = feedbackRepository.save(feedback);
            ratingCalculation(principal, course);
            return feed;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, feedback could not be saved");
        }
    }

    @Override
    public Feedback findById(long id) {
        return feedbackRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, FEEDBACK_NF.value));
    }

    @Override
    public Page<Feedback> findAll(Pageable pageable) {
        try {
            return feedbackRepository.findAll(pageable);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, feedback cannot be found");
        }
    }


    @Override
    @Transactional
    public void deleteById(Principal principal, long id) {
        Feedback oldFeedback = feedbackRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, FEEDBACK_NF.value));
        Course course = check(principal, oldFeedback);
        try {
            feedbackRepository.deleteById(id);
            ratingCalculation(principal, course);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                COULD_NOT_DELETE.value);
        }
    }

    private Course check(Principal principal, Feedback oldFeedback) {
        User user = userService.findById(oldFeedback.getUser().getId());
        if (!user.getUsername().equals(principal.getName())) {
            throw new CustomException(HttpStatus.FORBIDDEN,
                "Invalid request, access is denied");
        }
        return courseService.findById(oldFeedback.getCourse().getId());
    }

    @Override
    public Page<Feedback> getAllFeedbackByCourseId(Pageable pageable, long courseId) {
        Course course = courseService.findById(courseId);
        try {
            return feedbackRepository.findAllByCourseId(pageable, course.getId());
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, feedbacks cannot be found");
        }
    }

    @Override
    @Transactional
    public Feedback update(Principal principal, Feedback feedbackToUpdate) {
        Feedback oldFeedback = feedbackRepository.findById(feedbackToUpdate.getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, FEEDBACK_NF.value));
        Course course = check(principal, oldFeedback);
        oldFeedback.setDetention(feedbackToUpdate.getDetention());
        oldFeedback.setRank(feedbackToUpdate.getRank());
        try {
            Feedback feed = feedbackRepository.save(oldFeedback);
            ratingCalculation(principal, course);
            return feed;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, feedback could not be saved");
        }
    }

    private void ratingCalculation(Principal principal, Course course) {
        int averageRank = feedbackRepository.findAverageRankByCourseId(
            course.getId());
        course.setRating(averageRank);
        courseService.save(principal, course);
    }

}
