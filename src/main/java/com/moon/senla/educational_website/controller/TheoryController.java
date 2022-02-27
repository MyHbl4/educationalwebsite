package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.dto.mapper.TheoryMapper;
import com.moon.senla.educational_website.model.dto.theory.TheoryDto;
import com.moon.senla.educational_website.model.dto.theory.TheoryNewDto;
import com.moon.senla.educational_website.model.dto.theory.TheoryUpdateDto;
import com.moon.senla.educational_website.service.SearchFilterService;
import com.moon.senla.educational_website.service.TheoryService;
import io.swagger.annotations.Api;
import java.security.Principal;
import javax.validation.Valid;
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
    private final SearchFilterService searchFilterService;
    private final TheoryMapper theoryMapper;

    public TheoryController(TheoryService theoryService,
        SearchFilterService searchFilterService,
        TheoryMapper theoryMapper) {
        this.theoryService = theoryService;
        this.searchFilterService = searchFilterService;
        this.theoryMapper = theoryMapper;
    }

    @GetMapping(path = "/{id}")
    public TheoryDto findById(@PathVariable(name = "id") long id) {
        log.info("findById - find theory by id {}", id);
        Theory theory = theoryService.findById(id);
        return theoryMapper.theoryToTheoryDto(theory);
    }

    @PostMapping()
    public TheoryDto save(Principal principal, @Valid @RequestBody TheoryNewDto theory) {
        log.info("save - save theory by name: {}", theory.getName());
        Theory newTheory = theoryService.save(principal, theory);
        return theoryMapper.theoryToTheoryDto(newTheory);
    }

    @PutMapping()
    public TheoryDto update(Principal principal,
        @Valid @RequestBody TheoryUpdateDto theoryToUpdate) {
        log.info("update - update theory by id: {}", theoryToUpdate.getId());
        Theory theory = theoryService.update(principal, theoryToUpdate);
        return theoryMapper.theoryToTheoryDto(theory);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete - delete theory by id: {}", id);
        theoryService.deleteById(id);
    }

    @GetMapping(path = "/search")
    public Page<TheoryDto> findAllTheoriesByParam(
        @PageableDefault(sort = {"id"}) Pageable pageable,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "topic_name", required = false) String topicName,
        @RequestParam(value = "user_name", required = false) String userName) {
        log.info("findAllTheoriesByParam - find all theories by param");
        return searchFilterService.findAllTheoriesByParam(pageable, name, topicName, userName)
            .map(theoryMapper::theoryToTheoryDto);
    }
}