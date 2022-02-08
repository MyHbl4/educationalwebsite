package com.moon.senla.educational_website.model.dto.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moon.senla.educational_website.model.dto.group.GroupShortDto;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleNewDto {
    private LocalDateTime date;
    @JsonProperty("group")
    private GroupShortDto group;
}
