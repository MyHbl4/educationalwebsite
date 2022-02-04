package com.moon.senla.educational_website.controller;

import com.moon.senla.educational_website.model.dto.user.UserDtoShort;
import com.moon.senla.educational_website.service.ManagingSubscriptionsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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
    @PreAuthorize("#user.username == authentication.name")
    public void subscribeToGroup(UserDtoShort user, long groupId) {
        log.info("add user id:{} to group id:{}", user.getId(), groupId);
        managingSubscriptionsService.addUserToGroup(user.getId(), groupId);
    }


    @PostMapping(path = "/unsubscribe")
    @PreAuthorize("#user.username == authentication.name")
    public void unsubscribeFromGroup(UserDtoShort user, long groupId) {
        log.info("remove user id:{} from group id:{}", user.getId(), groupId);
        managingSubscriptionsService.removeUserFromGroup(user.getId(), groupId);
    }

    @PostMapping(path = "/remove-user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void removeUserFromGroup(long userId, long groupId) {
        log.info("remove user id:{} from group id:{}", userId, groupId);
        managingSubscriptionsService.removeUserFromGroup(userId, groupId);
    }
}
