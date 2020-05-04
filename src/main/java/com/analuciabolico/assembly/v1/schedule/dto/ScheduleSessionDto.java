package com.analuciabolico.assembly.v1.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.analuciabolico.assembly.v1.core.common.Time.defaultMinute;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleSessionDto implements Serializable {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ScheduleSessionDto convert() {
        this.endTime = endTime == null ? defaultMinute(this.startTime) : endTime;
        return this;
    }
}
