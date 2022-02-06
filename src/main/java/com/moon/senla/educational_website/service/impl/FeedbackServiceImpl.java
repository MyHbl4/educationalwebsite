package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.FeedbackRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.service.CourseService;
import com.moon.senla.educational_website.service.FeedbackService;
import java.util.Optional;
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

    @Autowired
    public FeedbackServiceImpl(
        FeedbackRepository feedbackRepository,
        CourseService courseService) {
        this.feedbackRepository = feedbackRepository;
        this.courseService = courseService;
    }

    @Override
    @Transactional
    public Feedback save(Feedback feedback) {
        try {
            Feedback feed = feedbackRepository.save(feedback);
            int averageRank = feedbackRepository.findAverageRankByCourseId(
                feed.getCourse().getId());
            Course course = courseService.findById(feed.getCourse().getId());
            course.setRating(averageRank);
            courseService.save(course);
            return feed;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, feedback could not be saved");
        }
    }

    @Override
    public Feedback findById(long id) {
        Feedback feedback = null;
        Optional<Feedback> option = feedbackRepository.findById(id);
        if (option.isPresent()) {
            feedback = option.get();
        }
        if (feedback == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Feedback Not Found");
        }
        return feedback;
    }

    @Override
    public Page<Feedback> findAll(Pageable pageable) {
        Page<Feedback> feedbacks = feedbackRepository.findAll(pageable);
        if (feedbacks.getContent().isEmpty()) {
            throw new CustomException(HttpStatus.NO_CONTENT,
                "Request has been successfully processed and the response is  blank. Feedbacks Not Found");
        }
        return feedbacks;
    }

    @Override
    public void deleteById(long id) {
        try {
            feedbackRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Feedback Not Found");
        }
    }

}
