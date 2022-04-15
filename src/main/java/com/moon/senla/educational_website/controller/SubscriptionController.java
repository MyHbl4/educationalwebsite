package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.service.ManagingSubscriptionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/subscription")
@Slf4j
@Api(tags = "Subscription")
public class SubscriptionController {

    private final ManagingSubscriptionsService managingSubscriptionsService;

    public SubscriptionController(
        ManagingSubscriptionsService managingSubscriptionsService) {
        this.managingSubscriptionsService = managingSubscriptionsService;
    }

    @ApiOperation(value = "Subscribe to the course")
    @PostMapping(path = "/subscribe")
    public ResponseEntity<Object> subscribeToGroup(Principal principal, String groupId) {
        log.info("subscribeToGroup - add user id: {} to group id: {}", principal.getName(),
            groupId);
        managingSubscriptionsService.addUserToGroup(principal.getName(), groupId);
        return new ResponseEntity<>("Subscribe done!", HttpStatus.OK);
    }

    @ApiOperation(value = "Unsubscribe from the course")
    @PostMapping(path = "/unsubscribe")
    public ResponseEntity<Object> unsubscribeFromGroup(Principal principal, String groupId) {
        log.info("unsubscribeFromGroup - remove user id: {} from group id: {}", principal.getName(),
            groupId);
        managingSubscriptionsService.unsubscribeUserFromGroup(principal.getName(), groupId);
        return new ResponseEntity<>("Unsubscribe done!", HttpStatus.OK);
    }

    @ApiOperation(value = "Kick user from group, only for admin")
    @PostMapping(path = "/remove-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> removeUserFromGroup(String userId, String groupId) {
        log.info("removeUserFromGroup - remove user id: {} from group id: {}", userId, groupId);
        managingSubscriptionsService.removeUserFromGroup(userId, groupId);
        return new ResponseEntity<>("User removed from group!", HttpStatus.OK);
    }
}
