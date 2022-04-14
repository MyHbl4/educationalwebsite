package com.moon.senla.educational_website.model.dto.schedule;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleUpdateDto {
    private String id;
    private LocalDateTime date;
}
