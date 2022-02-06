package com.moon.senla.educational_website.service;

public interface ManagingSubscriptionsService {

    void addUserToGroup(String username, long groupId);

    void removeUserFromGroup(String username, long groupId);
}
