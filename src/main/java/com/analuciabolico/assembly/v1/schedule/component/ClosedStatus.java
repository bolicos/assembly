package com.analuciabolico.assembly.v1.schedule.component;

import com.analuciabolico.assembly.v1.core.exceptions.model.InvalidOperationException;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import com.analuciabolico.assembly.v1.schedule.service.interfaces.IStatusScheduleSession;
import org.springframework.stereotype.Component;

import static com.analuciabolico.assembly.v1.core.validation.GenericMessagesValidationEnum.SESSION_STATUS_NOT_UPDATE;
import static com.analuciabolico.assembly.v1.core.validation.MessageValidationProperties.getMessage;

@Component
public class ClosedStatus implements IStatusScheduleSession {
    @Override
    public void cancel(Schedule schedule) {
        throw new InvalidOperationException(getMessage(SESSION_STATUS_NOT_UPDATE));
    }

    @Override
    public void create(Schedule schedule) {
        throw new InvalidOperationException(getMessage(SESSION_STATUS_NOT_UPDATE));
    }

    @Override
    public void close(Schedule schedule) {
        throw new InvalidOperationException(getMessage(SESSION_STATUS_NOT_UPDATE));
    }

    @Override
    public void open(Schedule schedule) {
        throw new InvalidOperationException(getMessage(SESSION_STATUS_NOT_UPDATE));
    }
}
