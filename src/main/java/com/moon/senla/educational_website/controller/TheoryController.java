package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.service.TheoryService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/theories")
public class TheoryController {

    private final TheoryService theoryService;

    public TheoryController(TheoryService theoryService) {
        this.theoryService = theoryService;
    }

    @GetMapping()
    public List<Theory> findAll() {
        return theoryService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Theory findById(@PathVariable(name = "id") long id) {
        return theoryService.findById(id);
    }

    @PostMapping()
    public Theory save(@RequestBody Theory theory) {
        return theoryService.save(theory);
    }

    @PutMapping()
    public Theory update(@RequestBody Theory theoryToUpdate) {
        return theoryService.save(theoryToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        theoryService.deleteById(id);
    }

    @GetMapping(path = "/topics/{id}")
    public List<Theory> findAllTheoriesByTopicId(@PathVariable(name = "id") long id) {
        return theoryService.findAllTheoriesByTopicId(id);
    }
}