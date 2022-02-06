package com.moon.senla.educational_website.service.impl;


import com.moon.senla.educational_website.dao.TheoryRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.service.TheoryService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
        try {
            return theoryRepository.save(theory);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, theory could not be saved");
        }
    }

    @Override
    public Theory findById(long id) {
        Theory theory = null;
        Optional<Theory> option = theoryRepository.findById(id);
        if (option.isPresent()) {
            theory = option.get();
        }
        if (theory == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Theory Not Found");
        }
        return theory;
    }

    @Override
    public Page<Theory> findAll(Pageable pageable) {
        Page<Theory> theories = theoryRepository.findAll(pageable);
        if (theories.getContent().isEmpty()) {
            throw new CustomException(HttpStatus.NO_CONTENT,
                "Request has been successfully processed and the response is  blank. Theories Not Found");
        }
        return theories;
    }


    @Override
    public void deleteById(long id) {
        try {
            theoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Theory Not Found");
        }
    }

}
