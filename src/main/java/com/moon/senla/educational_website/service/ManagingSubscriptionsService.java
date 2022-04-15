package com.moon.senla.educational_website.service;

public interface ManagingSubscriptionsService {

    void addUserToGroup(String username, String groupId);

    void removeUserFromGroup(String userId, String groupId);

    void unsubscribeUserFromGroup(String username, String groupId);
}
