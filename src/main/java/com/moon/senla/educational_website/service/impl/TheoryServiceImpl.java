package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.TheoryRepository;
import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.service.TheoryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TheoryServiceImpl implements TheoryService {

    private final TheoryRepository theoryRepository;

    @Autowired
    public TheoryServiceImpl(TheoryRepository theoryRepository) {
        this.theoryRepository = theoryRepository;
    }

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
    public Page<Theory> findAll(Pageable pageable) {
        return theoryRepository.findAll(pageable);
    }

    @Override
    public void deleteById(long id) {
        theoryRepository.deleteById(id);
    }

    @Override
    public Page<Theory> findAllTheoriesByParam(Pageable pageable, String name, String topicName,
        String userName) {
        Page<Theory> page = theoryRepository.findAll(pageable);
        List<Theory> allContent = page.getContent();
        List<Theory> theories = new ArrayList<>();
        for (Theory theory : allContent) {
            if (theory.getName().equals(name)) {
                theories.add(theory);
            } else if (topicName != null && theory.getTopic().getName().equals(topicName)) {
                theories.add(theory);
            } else if (userName != null && theory.getUser().getUsername().equals(userName)) {
                theories.add(theory);
            }
        }
        return new PageImpl<>(theories, pageable, theories.size());
    }
}
