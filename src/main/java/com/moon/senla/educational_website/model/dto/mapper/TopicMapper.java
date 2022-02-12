package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.dto.topic.TopicDto;
import com.moon.senla.educational_website.model.dto.topic.TopicNewDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    TopicDto topicToTopicDto(Topic topic);

    TopicNewDto topicToTopicNewDto(Topic topic);

    Topic topicDtoToTopic(TopicDto topicDto);

    Topic topicNewDtoToTopic(TopicNewDto topicNewDto);
}
