package com.moon.senla.educational_website.model.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDto {
    private Long id;
    private LocalDateTime date;
}
