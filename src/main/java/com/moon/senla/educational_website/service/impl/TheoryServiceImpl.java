package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.TheoryRepository;
import com.moon.senla.educational_website.dao.TopicRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.mapper.TheoryMapper;
import com.moon.senla.educational_website.model.dto.theory.TheoryNewDto;
import com.moon.senla.educational_website.model.dto.theory.TheoryUpdateDto;
import com.moon.senla.educational_website.service.TheoryService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TheoryServiceImpl implements TheoryService {

    private final TheoryRepository theoryRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public TheoryServiceImpl(TheoryRepository theoryRepository,
        UserRepository userRepository,
        TopicRepository topicRepository) {
        this.theoryRepository = theoryRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public Theory save(Principal principal, TheoryNewDto newTheory) {
        topicRepository.findById(newTheory.getTopic().getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Topic Not Found"));
        try {
            Theory theory = TheoryMapper.INSTANCE.theoryNewDtoToTheory(newTheory);
            theory.setUser(userRepository.findByUsername(principal.getName()));
            return theoryRepository.save(theory);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, theory could not be saved");
        }
    }

    @Override
    public Theory update(Principal principal, TheoryUpdateDto theory) {
        Theory oldTheory = theoryRepository.findById(theory.getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Theory Not Found"));
        User user = userRepository.findById(oldTheory.getUser().getId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "User Not Found"));
        if (!user.getUsername().equals(principal.getName())) {
            throw new CustomException(HttpStatus.FORBIDDEN,
                "Invalid request, access is denied");
        }
        oldTheory.setName(theory.getName());
        oldTheory.setDescription(theory.getDescription());
        try {
            return theoryRepository.save(oldTheory);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, theory could not be updated");
        }
    }

    @Override
    public Theory findById(long id) {
        return theoryRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Theory Not Found"));
    }

    @Override
    public void deleteById(long id) {
        theoryRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Theory Not Found"));
        try {
            theoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, failed to delete");
        }
    }

}
