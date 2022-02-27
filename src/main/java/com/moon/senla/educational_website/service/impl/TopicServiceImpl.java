package com.moon.senla.educational_website.service.impl;


import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.educational_website.utils.StringConstants.TOPIC_NF;

import com.moon.senla.educational_website.dao.TopicRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.dto.mapper.TopicMapper;
import com.moon.senla.educational_website.model.dto.topic.TopicDto;
import com.moon.senla.educational_website.model.dto.topic.TopicNewDto;
import com.moon.senla.educational_website.service.TopicService;
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
    public Topic save(TopicNewDto topic) {
        try {
            return topicRepository.save(TopicMapper.INSTANCE.topicNewDtoToTopic(topic));
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, topic could not be saved");
        }
    }

    @Override
    public Topic findById(long id) {
        return topicRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, TOPIC_NF.value));
    }

    @Override
    public Page<Topic> findAll(Pageable pageable) {
        try {
            return topicRepository.findAll(pageable);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, topic cannot be found");
        }
    }

    @Override
    public void deleteById(long id) {
        if (!topicRepository.findById(id).isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND, TOPIC_NF.value);
        }
        try {
            topicRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                COULD_NOT_DELETE.value);
        }
    }

    @Override
    public Topic update(TopicDto topicUpdate) {
        Topic oldTopic = topicRepository.findById(topicUpdate.getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, TOPIC_NF.value));
        oldTopic.setName(topicUpdate.getName());
        try {
            return topicRepository.save(oldTopic);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, topic could not be updated");
        }
    }
}
