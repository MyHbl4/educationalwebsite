package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.dto.mapper.CourseMapper;
import com.moon.senla.educational_website.model.dto.mapper.FeedbackMapper;
import com.moon.senla.educational_website.model.dto.mapper.GroupMapper;
import com.moon.senla.educational_website.model.dto.mapper.ScheduleMapper;
import com.moon.senla.educational_website.model.dto.mapper.TheoryMapper;
import com.moon.senla.educational_website.model.dto.mapper.TopicMapper;
import com.moon.senla.educational_website.model.dto.mapper.UserMapper;

public interface MapperService {

    CourseMapper getCourseMapper();

    GroupMapper getGroupMapper();

    FeedbackMapper getFeedbackMapper();

    ScheduleMapper getScheduleMapper();

    TheoryMapper getTheoryMapper();

    TopicMapper getTopicMapper();

    UserMapper getUserMapper();
}
