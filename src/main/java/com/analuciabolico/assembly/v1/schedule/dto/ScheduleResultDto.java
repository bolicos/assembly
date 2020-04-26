package com.analuciabolico.assembly.v1.schedule.dto;

import com.analuciabolico.assembly.v1.schedule.enums.ScheduleStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResultDto implements Serializable {
    private ScheduleStatusEnum status;
}
