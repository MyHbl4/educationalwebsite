package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.dto.group.GroupDto;
import com.moon.senla.educational_website.model.dto.group.GroupPageDto;
import com.moon.senla.educational_website.model.dto.mapper.GroupMapper;
import com.moon.senla.educational_website.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public GroupPageDto findAllPageable(@PageableDefault(sort = {"id"}, size = 3)
        Pageable pageable) {
        log.info("find all groups");
        return groupService.findAllPageable(pageable);
    }

    @GetMapping(path = "/{id}")
    public GroupDto findById(@PathVariable(name = "id") long id) {
        log.info("find group by id {}", id);
        Group group = groupService.findById(id);
        return GroupMapper.INSTANCE.groupToGroupDto(group);
    }

    @PostMapping()
    public GroupDto save(@RequestBody Group group) {
        log.info("save group {}", group);
        Group newGroup = groupService.save(group);
        return GroupMapper.INSTANCE.groupToGroupDto(newGroup);
    }

    @PutMapping()
    public GroupDto update(@RequestBody Group groupToUpdate) {
        log.info("update group {}", groupToUpdate);
        Group group = groupService.save(groupToUpdate);
        return GroupMapper.INSTANCE.groupToGroupDto(group);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete group by id {}", id);
        groupService.deleteById(id);
    }
}