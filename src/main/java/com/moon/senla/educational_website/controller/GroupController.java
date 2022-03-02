package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.Group;
import com.moon.senla.educational_website.model.dto.group.GroupDto;
import com.moon.senla.educational_website.model.dto.group.GroupNewDto;
import com.moon.senla.educational_website.model.dto.group.GroupShortDto;
import com.moon.senla.educational_website.model.dto.mapper.GroupMapper;
import com.moon.senla.educational_website.model.dto.mapper.ScheduleMapper;
import com.moon.senla.educational_website.model.dto.mapper.UserMapper;
import com.moon.senla.educational_website.model.dto.schedule.ScheduleDto;
import com.moon.senla.educational_website.model.dto.user.UserGroupDto;
import com.moon.senla.educational_website.service.GroupService;
import com.moon.senla.educational_website.service.ScheduleService;
import com.moon.senla.educational_website.service.UserService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/groups")
@Slf4j
@Api(tags = "Groups")
public class GroupController {

    private final GroupService groupService;
    private final ScheduleService scheduleService;
    private final UserService userService;
    private final GroupMapper groupMapper;
    private final ScheduleMapper scheduleMapper;
    private final UserMapper userMapper;

    public GroupController(GroupService groupService,
        ScheduleService scheduleService,
        UserService userService,
        GroupMapper groupMapper,
        ScheduleMapper scheduleMapper,
        UserMapper userMapper) {
        this.groupService = groupService;
        this.scheduleService = scheduleService;
        this.userService = userService;
        this.groupMapper = groupMapper;
        this.scheduleMapper = scheduleMapper;
        this.userMapper = userMapper;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Page<GroupDto> findAll(@PageableDefault(sort = {"id"})
        Pageable pageable) {
        log.info("findAll - find all groups");
        return groupService.findAll(pageable)
            .map(groupMapper::groupToGroupDto);
    }

    @GetMapping(path = "/{id}")
    public GroupDto findById(@PathVariable(name = "id") long id) {
        log.info("findById - find group by id: {}", id);
        Group group = groupService.findById(id);
        return groupMapper.groupToGroupDto(group);
    }

    @PostMapping()
    public GroupDto save(Principal principal, @Valid @RequestBody GroupNewDto group) {
        log.info("save - save group by name: {}", group.getName());
        Group newGroup = groupService.save(principal, groupMapper.groupNewDtoToGroup(group));
        return groupMapper.groupToGroupDto(newGroup);
    }

    @PutMapping()
    public GroupDto update(Principal principal, @Valid @RequestBody GroupShortDto groupToUpdate) {
        log.info("update - update group by id: {}", groupToUpdate.getId());
        Group group = groupService.update(principal,
            groupMapper.groupShortDtoToGroup(groupToUpdate));
        return groupMapper.groupToGroupDto(group);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable(name = "id") long id) {
        log.info("delete - delete group by id: {}", id);
        groupService.deleteById(id);
    }

    @GetMapping(path = "/{id}/schedules")
    public Page<ScheduleDto> findAllSchedulesByGroupId(@PathVariable(name = "id") long id,
        Pageable pageable) {
        log.info("findAllSchedulesByGroupId - find schedules by group id: {}", id);
        return scheduleService.findAllByGroupId(pageable, id)
            .map(scheduleMapper::scheduleToScheduleDto);
    }

    @GetMapping(path = "/{id}/users")
    public Page<UserGroupDto> getAllUsersByGroupId(Pageable pageable,
        @PathVariable(name = "id") long id) {
        log.info("getAllUsersByGroupId - find users by group id: {}", id);
        return userService.getAllUsersByGroupId(pageable, id)
            .map(userMapper::userToUserGroupDto);
    }
}