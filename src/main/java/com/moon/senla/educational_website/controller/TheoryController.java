package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.TheoryService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/theories")
@Slf4j
public class TheoryController {

    private final TheoryService theoryService;

    public TheoryController(TheoryService theoryService) {
        this.theoryService = theoryService;
    }

    @GetMapping()
    public List<Theory> findAll() {
        log.info("find all theories");
        return theoryService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Theory findById(@PathVariable(name = "id") long id) {
        log.info("find theory by id {}", id);
        return theoryService.findById(id);
    }

    @PostMapping()
    public Theory save(@RequestBody Theory theory) {
        log.info("save theory {}", theory);
        return theoryService.save(theory);
    }

    @PutMapping()
    public Theory update(@RequestBody Theory theoryToUpdate) {
        log.info("update theory {}", theoryToUpdate);
        return theoryService.save(theoryToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete theory by id {}", id);
        theoryService.deleteById(id);
    }

    @GetMapping(path = "/topics/{id}")
    public List<Theory> findAllTheoriesByTopicId(@PathVariable(name = "id") long id) {
        log.info("find all theories by topic id {}", id);
        return theoryService.findAllTheoriesByTopicId(id);
    }

    @GetMapping(path = "/find-needed")
    public List<Theory> findAllTheoriesByParam(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "topic", required = false) Topic topic,
        @RequestParam(value = "user", required = false) User user) {
        return theoryService.findAllTheoriesByParam(name, topic, user);
    }
}