package com.analuciabolico.assembly.v1.core.common;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class Time {

    public static LocalDateTime defaultMinute(LocalDateTime startTime) {
        int minute = startTime.getMinute();
        return minute == 59 ?
                LocalDateTime.of(
                        startTime.getYear(),
                        startTime.getMonth(),
                        startTime.getDayOfMonth(),
                        startTime.getHour() + 1,
                        0,
                        startTime.getSecond())
                :
                LocalDateTime.of(
                        startTime.getYear(),
                        startTime.getMonth(),
                        startTime.getDayOfMonth(),
                        startTime.getHour(),
                        startTime.getMinute() + 1,
                        startTime.getSecond());
    }

}
