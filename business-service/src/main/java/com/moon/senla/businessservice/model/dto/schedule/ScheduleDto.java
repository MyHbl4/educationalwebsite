package com.moon.senla.businessservice.model.dto.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moon.senla.businessservice.model.dto.group.GroupShortDto;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDto {
    private Long id;
    private LocalDateTime date;
    @JsonProperty("group")
    private GroupShortDto group;
}
