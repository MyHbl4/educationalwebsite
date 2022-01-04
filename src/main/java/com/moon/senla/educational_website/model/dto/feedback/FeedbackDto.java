package com.moon.senla.educational_website.model.dto.feedback;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDto {
    private Long id;
    private LocalDate date;
    private String detention;
    private int rank;
}
