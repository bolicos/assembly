package com.analuciabolico.assembly.v1.core;

import com.analuciabolico.assembly.v1.associated.dto.AssociatedDto;
import com.analuciabolico.assembly.v1.associated.model.Associated;
import com.analuciabolico.assembly.v1.core.model.ResourceCreated;
import com.analuciabolico.assembly.v1.schedule.dto.ScheduleDto;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static com.analuciabolico.assembly.v1.core.Constants.*;
import static com.analuciabolico.assembly.v1.core.Tags.RUN_FAST;
import static com.analuciabolico.assembly.v1.schedule.enums.ScheduleResultEnum.UNDEFINED;
import static com.analuciabolico.assembly.v1.schedule.enums.ScheduleStatusEnum.CREATED;

@Tag(RUN_FAST)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class BaseUnityTest {

    protected final String PRIMARY_KEY_VIOLATION = "primary key violation";

    protected ResourceCreated oneResourceCreated() {
        return new ResourceCreated(ONE_LONG);
    }

    protected ScheduleDto oneScheduleDto() {
        return new ScheduleDto(TITLE, DESCRIPTION);
    }

    protected Optional<Schedule> oneScheduleOptional() {
        return Optional.of(new Schedule(ONE_LONG, TITLE, DESCRIPTION, STATUS));
    }

    protected Optional<Schedule> oneScheduleOptionalEmpty() {
        return Optional.empty();
    }

    protected Schedule oneSchedule() {
        return new Schedule(ONE_LONG, TITLE, DESCRIPTION, CREATED, null, null, null, null, UNDEFINED);
    }

    protected Associated oneAssociated() {
        return new Associated(ONE_LONG, NAME, VALID_CPF, CREATED_AT);
    }

    protected Optional<Associated> oneAssociatedOptional() {
        return Optional.of(new Associated(ONE_LONG, NAME, VALID_CPF, CREATED_AT));
    }

    protected Optional<Associated> oneAssociatedOptionalEmpty() {
        return Optional.empty();
    }

    protected AssociatedDto oneAssociatedDto() {
        return new AssociatedDto(NAME, VALID_CPF);
    }

    protected DataIntegrityViolationException oneDataIntegrityViolationException() {
        return new DataIntegrityViolationException(PRIMARY_KEY_VIOLATION);
    }

    protected Sort oneSortAsc() {
        return Sort.by(Sort.DEFAULT_DIRECTION, "id");
    }

    protected List<Associated> oneAssociatedList() {
        return List.of(oneAssociated());
    }

    protected List<Associated> oneAssociatedListEmpty() {
        return List.of();
    }

    protected List<Schedule> oneScheduleList() {
        return List.of(oneSchedule());
    }

    protected List<Schedule> oneScheduleListEmpty() {
        return List.of();
    }
}
