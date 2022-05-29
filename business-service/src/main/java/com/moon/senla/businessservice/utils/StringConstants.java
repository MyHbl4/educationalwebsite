package com.moon.senla.businessservice.utils;

public enum StringConstants {

    USER_NF("User not found"),
    TOPIC_NF("Topic not found"),
    THEORY_NF("Theory not found"),
    SCHEDULE_NF("Schedule not found"),
    GROUP_NF("Group not found"),
    FEEDBACK_NF("Feedback not found"),
    COURSE_NF("Course not found"),
    COULD_NOT_DELETE("Invalid request, failed to delete"),
    COULD_NOT_UPDATED("Invalid request, could not be updated"),
    COULD_NOT_SAVED("Invalid request, could not be saved"),
    ACCESS_DENIED("Invalid request, access is denied");

    public final String value;

    StringConstants(String value) {
        this.value = value;
    }
}
