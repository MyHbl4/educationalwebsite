package com.moon.senla.educational_website.model.dto.schedule;

import com.moon.senla.educational_website.model.Schedule;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SchedulePageDto {
    private List<Schedule> schedules;
    private int currentPage;
    private int totalPages;
}
