package com.moon.senla.businessservice.model.dto.mapper;

import com.moon.senla.businessservice.model.Topic;
import com.moon.senla.businessservice.model.dto.topic.TopicDto;
import com.moon.senla.businessservice.model.dto.topic.TopicNewDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TopicMapper {

    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    TopicDto topicToTopicDto(Topic topic);

    TopicNewDto topicToTopicNewDto(Topic topic);

    Topic topicDtoToTopic(TopicDto topicDto);

    Topic topicNewDtoToTopic(TopicNewDto topicNewDto);
}
