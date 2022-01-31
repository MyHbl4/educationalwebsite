package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.dto.mapper.TheoryMapper;
import com.moon.senla.educational_website.model.dto.theory.TheoryDto;
import com.moon.senla.educational_website.service.TheoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Api(tags = "Theories")
public class TheoryController {

    private final TheoryService theoryService;

    public TheoryController(TheoryService theoryService) {
        this.theoryService = theoryService;
    }

    @GetMapping()
    public Page<TheoryDto> findAll(@PageableDefault(sort = {"id"})
        Pageable pageable) {
        log.info("find all theories");
        return theoryService.findAll(pageable)
            .map(TheoryMapper.INSTANCE::theoryToTheoryDto);
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
    @PreAuthorize("#theoryToUpdate.user.username == authentication.name")
    public TheoryDto update(@RequestBody Theory theoryToUpdate) {
        log.info("update theory {}", theoryToUpdate);
        Theory theory = theoryService.save(theoryToUpdate);
        return TheoryMapper.INSTANCE.theoryToTheoryDto(theory);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete theory by id {}", id);
        theoryService.deleteById(id);
    }

    @GetMapping(path = "/topics/{id}")
    public Page<TheoryDto> findAllTheoriesByTopicId(@PathVariable(name = "id") long id,
        @PageableDefault(sort = {"id"})
            Pageable pageable) {
        log.info("find all theories by topic id {}", id);
        return theoryService.findAllTheoriesByTopicId(id, pageable)
            .map(TheoryMapper.INSTANCE::theoryToTheoryDto);
    }

    @GetMapping(path = "/find-needed")
    public Page<TheoryDto> findAllTheoriesByParam(
        @PageableDefault(sort = {"id"}) Pageable pageable,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "topic_name", required = false) String topicName,
        @RequestParam(value = "user_name", required = false) String userName) {
        return theoryService.findAllTheoriesByParam(pageable, name, topicName, userName)
            .map(TheoryMapper.INSTANCE::theoryToTheoryDto);
    }
}