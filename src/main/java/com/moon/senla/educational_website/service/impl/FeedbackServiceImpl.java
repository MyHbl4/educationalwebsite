package com.moon.senla.educational_website.service.impl;


import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.educational_website.utils.StringConstants.COURSE_NF;
import static com.moon.senla.educational_website.utils.StringConstants.FEEDBACK_NF;
import static com.moon.senla.educational_website.utils.StringConstants.USER_NF;

import com.moon.senla.educational_website.dao.CourseRepository;
import com.moon.senla.educational_website.dao.FeedbackRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Feedback;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackNewDto;
import com.moon.senla.educational_website.model.dto.feedback.FeedbackUpdateDto;
import com.moon.senla.educational_website.model.dto.mapper.FeedbackMapper;
import com.moon.senla.educational_website.service.FeedbackService;
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
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public FeedbackServiceImpl(
        FeedbackRepository feedbackRepository,
        CourseRepository courseRepository,
        UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Feedback save(Principal principal, FeedbackNewDto newFeedback) {
        Feedback feedback = FeedbackMapper.INSTANCE.feedbackNewDtoToFeedback(newFeedback);
        User user = userRepository.findByUsername(principal.getName());
        Course course = courseRepository.findById(feedback.getCourse().getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, COURSE_NF.value));
        feedback.setUser(user);
        feedback.setCourse(course);
        try {
            Feedback feed = feedbackRepository.save(feedback);
            int averageRank = feedbackRepository.findAverageRankByCourseId(
                feed.getCourse().getId());
            course.setRating(averageRank);
            courseRepository.save(course);
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
        User user = userRepository.findById(oldFeedback.getUser().getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, USER_NF.value));
        if (!user.getUsername().equals(principal.getName())) {
            throw new CustomException(HttpStatus.FORBIDDEN,
                "Invalid request, access is denied");
        }
        Course course = courseRepository.findById(oldFeedback.getCourse().getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, COURSE_NF.value));
        try {
            feedbackRepository.deleteById(id);
            int averageRank = feedbackRepository.findAverageRankByCourseId(
                course.getId());
            course.setRating(averageRank);
            courseRepository.save(course);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                COULD_NOT_DELETE.value);
        }
    }

    @Override
    public Page<Feedback> getAllFeedbackByCourseId(Pageable pageable, long courseId) {
        courseRepository.findById(courseId)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, COURSE_NF.value));
        try {
            return feedbackRepository.findAllByCourseId(pageable, courseId);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, feedbacks cannot be found");
        }
    }

    @Override
    @Transactional
    public Feedback update(Principal principal, FeedbackUpdateDto feedbackToUpdate) {
        Feedback oldFeedback = feedbackRepository.findById(feedbackToUpdate.getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, FEEDBACK_NF.value));
        User user = userRepository.findById(oldFeedback.getUser().getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, USER_NF.value));
        if (!user.getUsername().equals(principal.getName())) {
            throw new CustomException(HttpStatus.FORBIDDEN,
                "Invalid request, access is denied");
        }
        Course course = courseRepository.findById(oldFeedback.getCourse().getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, COURSE_NF.value));
        oldFeedback.setDetention(feedbackToUpdate.getDetention());
        oldFeedback.setRank(feedbackToUpdate.getRank());
        try {
            Feedback feed = feedbackRepository.save(oldFeedback);
            int averageRank = feedbackRepository.findAverageRankByCourseId(
                feed.getCourse().getId());
            course.setRating(averageRank);
            courseRepository.save(course);
            return feed;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, feedback could not be saved");
        }
    }

}
