package com.analuciabolico.assembly.v1.schedule.dto;

import com.analuciabolico.assembly.v1.schedule.enums.ScheduleResultEnum;
import com.analuciabolico.assembly.v1.schedule.enums.ScheduleStatusEnum;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto implements Serializable {
    private String title;
    private String description;

    public Schedule convertToSchedule() {
        return new Schedule(
                null,
                this.title,
                this.description,
                ScheduleStatusEnum.CREATED,
                null,
                null,
                null,
                null,
                ScheduleResultEnum.UNDEFINED);
    }
}
