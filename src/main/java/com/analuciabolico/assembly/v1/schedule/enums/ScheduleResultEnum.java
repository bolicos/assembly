package com.analuciabolico.assembly.v1.schedule.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScheduleResultEnum {
    APPROVED("Approved"),
    DISAPPROVED("Disapproved"),
    UNDEFINED("Undefined"),
    DRAW("Draw");

    String key;
}
