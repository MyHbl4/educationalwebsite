package com.moon.senla.educational_website.service;

public interface ManagingSubscriptionsService {

    void addUserToGroup(long userId, long groupId);

    void removeUserFromGroup(long userId, long groupId);
}
