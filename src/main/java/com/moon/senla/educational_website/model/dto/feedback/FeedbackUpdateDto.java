package com.moon.senla.educational_website.model.dto.feedback;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackUpdateDto {
    private Long id;
    private String detention;
    private int rank;
}
