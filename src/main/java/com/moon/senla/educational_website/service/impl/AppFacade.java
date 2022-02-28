package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.dto.user.UserDto;
import com.moon.senla.educational_website.service.AuthenticationService;
import com.moon.senla.educational_website.service.CourseService;
import com.moon.senla.educational_website.service.IFacade;
import com.moon.senla.educational_website.service.FeedbackService;
import com.moon.senla.educational_website.service.GroupService;
import com.moon.senla.educational_website.service.ManagingSubscriptionsService;
import com.moon.senla.educational_website.service.MapperService;
import com.moon.senla.educational_website.service.ScheduleService;
import com.moon.senla.educational_website.service.SearchFilterService;
import com.moon.senla.educational_website.service.TheoryService;
import com.moon.senla.educational_website.service.TopicService;
import com.moon.senla.educational_website.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppFacade implements IFacade {

    private final AuthenticationService authenticationService;
    private final CourseService courseService;
    private final FeedbackService feedbackService;
    private final GroupService groupService;
    private final ManagingSubscriptionsService managingSubscriptionsService;
    private final ScheduleService scheduleService;
    private final TheoryService theoryService;
    private final TopicService topicService;
    private final UserService userService;
    private final SearchFilterService searchFilterService;
    private final MapperService mapperService;

    @Autowired
    public AppFacade(
        AuthenticationService authenticationService,
        CourseService courseService,
        FeedbackService feedbackService,
        GroupService groupService,
        ManagingSubscriptionsService managingSubscriptionsService,
        ScheduleService scheduleService,
        TheoryService theoryService,
        TopicService topicService, UserService userService,
        SearchFilterService searchFilterService,
        MapperService mapperService) {
        this.authenticationService = authenticationService;
        this.courseService = courseService;
        this.feedbackService = feedbackService;
        this.groupService = groupService;
        this.managingSubscriptionsService = managingSubscriptionsService;
        this.scheduleService = scheduleService;
        this.theoryService = theoryService;
        this.topicService = topicService;
        this.userService = userService;
        this.searchFilterService = searchFilterService;
        this.mapperService = mapperService;
    }

    public Page<UserDto> findAllUsersByParam(Pageable pageable, String firstName, String lastName){
        try {
            return searchFilterService.findAllUsersByParam(pageable, firstName, lastName).map(mapperService.getUserMapper()::userToUserDto);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, users cannot be found");
        }
    }
}
