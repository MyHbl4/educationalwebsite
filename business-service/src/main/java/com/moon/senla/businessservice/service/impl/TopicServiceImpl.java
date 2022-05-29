package com.moon.senla.businessservice.service.impl;


import static com.moon.senla.businessservice.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.businessservice.utils.StringConstants.COULD_NOT_SAVED;
import static com.moon.senla.businessservice.utils.StringConstants.COULD_NOT_UPDATED;
import static com.moon.senla.businessservice.utils.StringConstants.TOPIC_NF;

import com.moon.senla.businessservice.dao.TopicRepository;
import com.moon.senla.businessservice.error.NotFoundException;
import com.moon.senla.businessservice.error.ValidationException;
import com.moon.senla.businessservice.model.Topic;
import com.moon.senla.businessservice.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            throw new ValidationException(COULD_NOT_SAVED.value);
        }
    }

    @Override
    public Topic findById(long id) {
        return topicRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(TOPIC_NF.value));
    }

    @Override
    public Page<Topic> findAll(Pageable pageable) {
        try {
            return topicRepository.findAll(pageable);
        } catch (Exception e) {
            throw new NotFoundException(TOPIC_NF.value);
        }
    }

    @Override
    public void deleteById(long id) {
        if (!topicRepository.findById(id).isPresent()) {
            throw new NotFoundException(TOPIC_NF.value);
        }
        try {
            topicRepository.deleteById(id);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_DELETE.value);
        }
    }

    @Override
    public Topic update(Topic topicUpdate) {
        Topic oldTopic = topicRepository.findById(topicUpdate.getId())
            .orElseThrow(() -> new NotFoundException(TOPIC_NF.value));
        oldTopic.setName(topicUpdate.getName());
        try {
            return topicRepository.save(oldTopic);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_UPDATED.value);
        }
    }
}
