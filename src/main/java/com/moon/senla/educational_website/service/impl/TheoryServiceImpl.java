package com.moon.senla.educational_website.service.impl;


import static com.moon.senla.educational_website.utils.StringConstants.ACCESS_DENIED;
import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_SAVED;
import static com.moon.senla.educational_website.utils.StringConstants.COULD_NOT_UPDATED;
import static com.moon.senla.educational_website.utils.StringConstants.THEORY_NF;

import com.moon.senla.educational_website.dao.TheoryRepository;
import com.moon.senla.educational_website.error.AuthException;
import com.moon.senla.educational_website.error.ValidationException;
import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.TheoryService;
import com.moon.senla.educational_website.service.TopicService;
import com.moon.senla.educational_website.service.UserService;
import java.security.Principal;
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
public class TheoryServiceImpl implements TheoryService {

    private final TheoryRepository theoryRepository;
    private final UserService userService;
    private final TopicService topicService;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public TheoryServiceImpl(TheoryRepository theoryRepository,
        UserService userService,
        TopicService topicService,
        MongoTemplate mongoTemplate) {
        this.theoryRepository = theoryRepository;
        this.userService = userService;
        this.topicService = topicService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Theory save(Principal principal, Theory newTheory) {
        try {
            newTheory.setUser(userService.findByUsername(principal.getName()));
            newTheory.setTopic(topicService.findById(newTheory.getTopic().getId()));
            return theoryRepository.save(newTheory);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_SAVED.value);
        }
    }

    @Override
    public Theory update(Principal principal, Theory theory) {
        Theory oldTheory = findById(theory.getId());
        User user = userService.findById(oldTheory.getUser().getId());
        if (!user.getUsername().equals(principal.getName())) {
            throw new AuthException(ACCESS_DENIED.value);
        }
        oldTheory.setName(theory.getName());
        oldTheory.setDescription(theory.getDescription());
        try {
            return theoryRepository.save(oldTheory);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_UPDATED.value);
        }
    }

    @Override
    public Theory findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Id").is(id));
        Theory theory = mongoTemplate.findOne(query, Theory.class);
        if (theory == null) {
            log.warn("Theory with id {} not found", id);
            throw new ValidationException(THEORY_NF.value);
        }
        log.info("Theory with id {} found", id);
        return theory;
    }

    @Override
    public void deleteById(String id) {
        findById(id);
        try {
            theoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_DELETE.value);
        }
    }

    @Override
    public Page<Theory> findAll(Pageable pageable) {
        return theoryRepository.findAll(pageable);
    }

//    @Override
//    public Page<Theory> findAllTheoryByParam(Pageable pageable,
//        String name, String topic, String username) {
//        try {
//            return theoryRepository.findAllTheoryByParam(pageable, name, topic, username);
//        } catch (Exception e) {
//            throw new NotFoundException(THEORY_NF.value);
//        }
//    }

}
