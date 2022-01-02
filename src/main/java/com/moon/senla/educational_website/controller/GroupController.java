package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.service.GroupService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/groups")
@Slf4j
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping()
    public List<Group> findAll() {
        log.info("find all groups");
        return groupService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Group findById(@PathVariable(name = "id") long id) {
        log.info("find group by id {}", id);
        return groupService.findById(id);
    }

    @PostMapping()
    public Group save(@RequestBody Group group) {
        log.info("save group {}", group);
        return groupService.save(group);
    }

    @PutMapping()
    public Group update(@RequestBody Group groupToUpdate) {
        log.info("update group {}", groupToUpdate);
        return groupService.save(groupToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete group by id {}", id);
        groupService.deleteById(id);
    }
}