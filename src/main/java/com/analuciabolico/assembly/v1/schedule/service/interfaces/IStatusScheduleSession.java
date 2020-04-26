package com.analuciabolico.assembly.v1.schedule.service.interfaces;

import com.analuciabolico.assembly.v1.schedule.model.Schedule;

public interface IStatusScheduleSession {
    void cancel(Schedule schedule);
    void create(Schedule schedule);
    void close(Schedule schedule);
    void open(Schedule schedule);
}
