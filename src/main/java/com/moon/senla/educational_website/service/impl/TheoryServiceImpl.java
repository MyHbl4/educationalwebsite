package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.TheoryRepository;
import com.moon.senla.educational_website.dao.TopicRepository;
import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.TheoryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TheoryServiceImpl implements TheoryService {

    @Autowired
    private final TheoryRepository theoryRepository;
    @Autowired
    private final TopicRepository topicRepository;

    @Override
    public Theory save(Theory theory) {
        return theoryRepository.save(theory);
    }

    @Override
    public Theory findById(long id) {
        Theory theory = null;
        Optional<Theory> option = theoryRepository.findById(id);
        if (option.isPresent()) {
            theory = option.get();
        }
        return theory;
    }

    @Override
    public List<Theory> findAll() {
        return theoryRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        theoryRepository.deleteById(id);
    }

    @Override
    public List<Theory> findAllTheoriesByTopicId(long id) {
        List<Theory> allTheories = theoryRepository.findAll();
        List<Theory> theories = new ArrayList<>();
        for (Theory theory : allTheories) {
            if (theory.getTopic().getId().equals(id)) {
                theories.add(theory);
            }
        }
        return theories;
    }

    @Override
    public List<Theory> findAllTheoriesByParam(String name, Topic topic, User user) {
        List<Theory> allTheories = theoryRepository.findAll();
        List<Theory> theories = new ArrayList<>();
        for (Theory theory : allTheories) {
            if (theory.getName().equals(name)) {
                theories.add(theory);
            } if (topic != null && theory.getTopic().getName().equals(topic.getName())) {
                theories.add(theory);
            } if (user != null && theory.getUser().getUsername().equals(user.getUsername())) {
                theories.add(theory);
            }
        }
        return theories;
    }
}
