package com.moon.senla.educational_website.model.dto.feedback;

import com.moon.senla.educational_website.model.Feedback;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FeedbackPageDto {
    private List<Feedback> feedbacks;
    private int currentPage;
    private int totalPages;
}
