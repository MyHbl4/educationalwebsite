package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.TopicRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.service.TopicService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Topic save(Topic topic) {
        try {
            return topicRepository.save(topic);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, topic could not be saved");
        }
    }

    @Override
    public Topic findById(long id) {
        Topic topic = null;
        Optional<Topic> option = topicRepository.findById(id);
        if (option.isPresent()) {
            topic = option.get();
        }
        if (topic == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Topic Not Found");
        }
        return topic;
    }

    @Override
    public Page<Topic> findAll(Pageable pageable) {

        Page<Topic> topics = topicRepository.findAll(pageable);
        if (topics.getContent().isEmpty()) {
            throw new CustomException(HttpStatus.NO_CONTENT,
                "Request has been successfully processed and the response is  blank. Topics Not Found");
        }
        return topics;
    }

    @Override
    public void deleteById(long id) {
        try {
            topicRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Topic Not Found");
        }
    }
}
