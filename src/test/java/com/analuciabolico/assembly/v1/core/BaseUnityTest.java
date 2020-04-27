package com.analuciabolico.assembly.v1.core;

import com.analuciabolico.assembly.v1.core.model.ResourceCreated;
import com.analuciabolico.assembly.v1.schedule.dto.ScheduleDto;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static com.analuciabolico.assembly.v1.core.Constants.*;
import static com.analuciabolico.assembly.v1.core.Tags.RUN_FAST;
import static com.analuciabolico.assembly.v1.schedule.enums.ScheduleResultEnum.UNDEFINED;
import static com.analuciabolico.assembly.v1.schedule.enums.ScheduleStatusEnum.CREATED;

@Tag(RUN_FAST)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class BaseUnityTest {
    protected ResourceCreated oneResourceCreated() {
        return new ResourceCreated(ONE_LONG);
    }

    protected ScheduleDto oneScheduleDto() {
        return new ScheduleDto(TITLE, DESCRIPTION);
    }

    protected Schedule oneSchedule() {
        return new Schedule(ONE_LONG,TITLE, DESCRIPTION, CREATED, null, null, null, null, UNDEFINED);
    }

}
