package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.FeedbackRepository;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.service.CourseService;
import com.moon.senla.educational_website.service.FeedbackService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final CourseService courseService;

    @Autowired
    public FeedbackServiceImpl(
        FeedbackRepository feedbackRepository,
        CourseService courseService) {
        this.feedbackRepository = feedbackRepository;
        this.courseService = courseService;
    }

    @Override
    public Feedback save(Feedback feedback) {
        Feedback feed = feedbackRepository.save(feedback);
        int averageRank = feedbackRepository.findAverageRankByCourseId(feed.getCourse().getId());
        Course course = courseService.findById(feed.getCourse().getId());
        course.setRating(averageRank);
        courseService.save(course);
        return feed;
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
