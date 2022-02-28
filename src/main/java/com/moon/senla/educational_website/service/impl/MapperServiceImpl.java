package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.model.dto.mapper.CourseMapper;
import com.moon.senla.educational_website.model.dto.mapper.FeedbackMapper;
import com.moon.senla.educational_website.model.dto.mapper.GroupMapper;
import com.moon.senla.educational_website.model.dto.mapper.ScheduleMapper;
import com.moon.senla.educational_website.model.dto.mapper.TheoryMapper;
import com.moon.senla.educational_website.model.dto.mapper.TopicMapper;
import com.moon.senla.educational_website.model.dto.mapper.UserMapper;
import com.moon.senla.educational_website.service.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceImpl implements MapperService {

    private final CourseMapper courseMapper;
    private final GroupMapper groupMapper;
    private final FeedbackMapper feedbackMapper;
    private final ScheduleMapper scheduleMapper;
    private final TheoryMapper theoryMapper;
    private final TopicMapper topicMapper;
    private final UserMapper userMapper;

    @Autowired
    public MapperServiceImpl(
        CourseMapper courseMapper,
        GroupMapper groupMapper,
        FeedbackMapper feedbackMapper,
        ScheduleMapper scheduleMapper,
        TheoryMapper theoryMapper,
        TopicMapper topicMapper,
        UserMapper userMapper) {
        this.courseMapper = courseMapper;
        this.groupMapper = groupMapper;
        this.feedbackMapper = feedbackMapper;
        this.scheduleMapper = scheduleMapper;
        this.theoryMapper = theoryMapper;
        this.topicMapper = topicMapper;
        this.userMapper = userMapper;
    }

    @Override
    public CourseMapper getCourseMapper() {
        return courseMapper;
    }

    @Override
    public GroupMapper getGroupMapper() {
        return groupMapper;
    }

    @Override
    public FeedbackMapper getFeedbackMapper() {
        return feedbackMapper;
    }

    @Override
    public ScheduleMapper getScheduleMapper() {
        return scheduleMapper;
    }

    @Override
    public TheoryMapper getTheoryMapper() {
        return theoryMapper;
    }

    @Override
    public TopicMapper getTopicMapper() {
        return topicMapper;
    }

    @Override
    public UserMapper getUserMapper() {
        return userMapper;
    }
}
