package com.analuciabolico.assembly.v1.schedule.enums;

import com.analuciabolico.assembly.v1.core.exceptions.model.InvalidOperationException;
import com.analuciabolico.assembly.v1.schedule.component.CanceledStatus;
import com.analuciabolico.assembly.v1.schedule.component.ClosedStatus;
import com.analuciabolico.assembly.v1.schedule.component.CreatedStatus;
import com.analuciabolico.assembly.v1.schedule.component.OpenStatus;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import com.analuciabolico.assembly.v1.schedule.service.interfaces.IStatusScheduleSession;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.analuciabolico.assembly.v1.core.validation.GenericMessagesValidationEnum.SESSION_STATUS_NOT_FOUND;
import static com.analuciabolico.assembly.v1.core.validation.MessageValidationProperties.getMessage;

@Getter
@AllArgsConstructor
public enum ScheduleStatusEnum {
    CREATED("Session created", new CreatedStatus()),
    OPEN("Open session", new OpenStatus()),
    CLOSE("Closed session", new ClosedStatus()),
    CANCELED("Session canceled", new CanceledStatus());

    String key;
    IStatusScheduleSession statusEnum;

    public static void update(Schedule schedule, ScheduleStatusEnum status) {
        switch (status) {
            case CREATED -> schedule.getStatus().getStatusEnum().create(schedule);
            case OPEN -> schedule.getStatus().getStatusEnum().open(schedule);
            case CLOSE -> schedule.getStatus().getStatusEnum().close(schedule);
            case CANCELED -> schedule.getStatus().getStatusEnum().cancel(schedule);
            default -> throw new InvalidOperationException(getMessage(SESSION_STATUS_NOT_FOUND));
        }
    }
}
