package com.moon.senla.businessservice.service;

public interface ManagingSubscriptionsService {

    void addUserToGroup(String username, long groupId);

    void removeUserFromGroup(long userId, long groupId);

    void unsubscribeUserFromGroup(String username, long groupId);
}
