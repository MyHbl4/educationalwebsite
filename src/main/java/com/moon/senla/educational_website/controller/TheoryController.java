package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.TheoryDto;
import com.moon.senla.educational_website.model.dto.mapper.TheoryMapper;
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
    public List<TheoryDto> findAll() {
        log.info("find all theories");
        List<Theory> theory = theoryService.findAll();
        return TheoryMapper.INSTANCE.listToDtoList(theory);
    }

    @GetMapping(path = "/{id}")
    public TheoryDto findById(@PathVariable(name = "id") long id) {
        log.info("find theory by id {}", id);
        Theory theory = theoryService.findById(id);
        return TheoryMapper.INSTANCE.theoryToTheoryDto(theory);
    }

    @PostMapping()
    public TheoryDto save(@RequestBody Theory theory) {
        log.info("save theory {}", theory);
        Theory newTheory = theoryService.save(theory);
        return TheoryMapper.INSTANCE.theoryToTheoryDto(newTheory);
    }

    @PutMapping()
    public TheoryDto update(@RequestBody Theory theoryToUpdate) {
        log.info("update theory {}", theoryToUpdate);
        Theory theory = theoryService.save(theoryToUpdate);
        return TheoryMapper.INSTANCE.theoryToTheoryDto(theory);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete theory by id {}", id);
        theoryService.deleteById(id);
    }

    @GetMapping(path = "/topics/{id}")
    public List<TheoryDto> findAllTheoriesByTopicId(@PathVariable(name = "id") long id) {
        log.info("find all theories by topic id {}", id);
        List<Theory> theory = theoryService.findAllTheoriesByTopicId(id);
        return TheoryMapper.INSTANCE.listToDtoList(theory);
    }

    @GetMapping(path = "/find-needed")
    public List<TheoryDto> findAllTheoriesByParam(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "topic", required = false) Topic topic,
        @RequestParam(value = "user", required = false) User user) {
        List<Theory> theory = theoryService.findAllTheoriesByParam(name, topic, user);
        return TheoryMapper.INSTANCE.listToDtoList(theory);
    }
}