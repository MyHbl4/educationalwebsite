package com.moon.senla.educational_website.service;

import java.security.Principal;

public interface ManagingSubscriptionsService {

    void addUserToGroup(long userId, long groupId);

    void removeUserFromGroup(long userId, long groupId);
}
