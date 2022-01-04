package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.TopicRepository;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.service.TopicService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    @Autowired
    private final TopicRepository topicRepository;

    @Override
    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public Topic findById(long id) {
        Topic topic = null;
        Optional<Topic> option = topicRepository.findById(id);
        if (option.isPresent()) {
            topic = option.get();
        }
        return topic;
    }

    @Override
    public Page<Topic> findAll(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    @Override
    public void deleteById(long id) {
        topicRepository.deleteById(id);
    }
}
