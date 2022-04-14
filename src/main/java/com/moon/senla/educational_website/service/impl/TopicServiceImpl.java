package com.moon.senla.educational_website.service.impl;


import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_UPDATED;
import static com.moon.senla.educational_website.utils.StringConstants.TOPIC_IS_EXIST;
import static com.moon.senla.educational_website.utils.StringConstants.TOPIC_NF;

import com.moon.senla.educational_website.dao.TopicRepository;
import com.moon.senla.educational_website.error.NotFoundException;
import com.moon.senla.educational_website.error.ValidationException;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository,
        MongoTemplate mongoTemplate) {
        this.topicRepository = topicRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Topic save(Topic topic) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(topic.getName()));
        Topic topicFromBd = mongoTemplate.findOne(query, Topic.class);
        if (topicFromBd != null) {
            log.warn("save - topic with name: {} already exists", topic.getName());
            throw new ValidationException(TOPIC_IS_EXIST.value);
        } else {
            log.info("save - topic with name: {} saved", topic.getName());
            return topicRepository.save(topic);
        }
    }


    @Override
    public Topic findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Id").is(id));
        Topic topic = mongoTemplate.findOne(query, Topic.class);
        if (topic == null) {
            log.warn("findById - no topic found by id: {}", id);
            throw new NotFoundException(TOPIC_NF.value);
        }
        log.info("findById - topic found by id: {}", id);
        return topic;
    }

    @Override
    public Page<Topic> findAll(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    @Override
    public void deleteById(String id) {
        findById(id);
        try {
            topicRepository.deleteById(id);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_DELETE.value);
        }
    }

    @Override
    public Topic update(Topic topicUpdate) {
        Topic oldTopic = findById(topicUpdate.getId());
        oldTopic.setName(topicUpdate.getName());
        try {
            return topicRepository.save(oldTopic);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_UPDATED.value);
        }
    }
}
