package com.moon.senla.educational_website.model.dto.topic;

import com.moon.senla.educational_website.model.Topic;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopicPageDto {
    private List<Topic> topics;
    private int currentPage;
    private int totalPages;
}
