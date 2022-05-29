package com.moon.senla.businessservice.service.impl;


import static com.moon.senla.businessservice.utils.StringConstants.ACCESS_DENIED;
import static com.moon.senla.businessservice.utils.StringConstants.COULD_NOT_DELETE;
import static com.moon.senla.businessservice.utils.StringConstants.COULD_NOT_SAVED;
import static com.moon.senla.businessservice.utils.StringConstants.COULD_NOT_UPDATED;
import static com.moon.senla.businessservice.utils.StringConstants.THEORY_NF;

import com.moon.senla.businessservice.dao.TheoryRepository;
import com.moon.senla.businessservice.error.AuthException;
import com.moon.senla.businessservice.error.NotFoundException;
import com.moon.senla.businessservice.error.ValidationException;
import com.moon.senla.businessservice.model.Theory;
import com.moon.senla.businessservice.model.User;
import com.moon.senla.businessservice.service.TheoryService;
import com.moon.senla.businessservice.service.TopicService;
import com.moon.senla.businessservice.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TheoryServiceImpl implements TheoryService {

    private final TheoryRepository theoryRepository;
    private final UserService userService;
    private final TopicService topicService;

    @Autowired
    public TheoryServiceImpl(TheoryRepository theoryRepository,
        UserService userService,
        TopicService topicService) {
        this.theoryRepository = theoryRepository;
        this.userService = userService;
        this.topicService = topicService;
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
        Theory oldTheory = theoryRepository.findById(theory.getId())
            .orElseThrow(() -> new NotFoundException(THEORY_NF.value));
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
    public Theory findById(long id) {
        return theoryRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(THEORY_NF.value));
    }

    @Override
    public void deleteById(long id) {
        if (!theoryRepository.findById(id).isPresent()) {
            throw new NotFoundException(THEORY_NF.value);
        }
        try {
            theoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_DELETE.value);
        }
    }

    @Override
    public Page<Theory> findAllTheoryByParam(Pageable pageable,
        String name, String topic, String username) {
        try {
            return theoryRepository.findAllTheoryByParam(pageable, name, topic, username);
        } catch (Exception e) {
            throw new NotFoundException(THEORY_NF.value);
        }
    }

}
