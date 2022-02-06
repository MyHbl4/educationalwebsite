package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.service.ManagingSubscriptionsService;
import io.swagger.annotations.Api;
import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ ClassName  :SubscriptionController
 * @ Author     :gmoon
 * @ Description:
 */

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

    @PostMapping(path = "/subscribe")
    public ResponseEntity<Object> subscribeToGroup(Principal user, long groupId) {
        log.info("add user id:{} to group id:{}", user.getName(), groupId);
        managingSubscriptionsService.addUserToGroup(user.getName(), groupId);
        return new ResponseEntity<>("Subscribe done!", HttpStatus.OK);
    }


    @PostMapping(path = "/unsubscribe")
    public ResponseEntity<Object> unsubscribeFromGroup(Principal user, long groupId) {
        log.info("remove user id:{} from group id:{}", user.getName(), groupId);
        managingSubscriptionsService.removeUserFromGroup(user.getName(), groupId);
        return new ResponseEntity<>("Unsubscribe done!", HttpStatus.OK);
    }

    @PostMapping(path = "/remove-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> removeUserFromGroup(String username, long groupId) {
        log.info("remove user id:{} from group id:{}", username, groupId);
        managingSubscriptionsService.removeUserFromGroup(username, groupId);
        return new ResponseEntity<>("User removed from group!", HttpStatus.OK);
    }
}
