package com.moon.senla.educational_website.model.dto.mapper;

import com.moon.senla.educational_website.model.Topic;
import com.moon.senla.educational_website.model.dto.topic.TopicDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    TopicDto topicToTopicDto(Topic topic);

    List<TopicDto> listToDtoList(List<Topic> topics);
}
