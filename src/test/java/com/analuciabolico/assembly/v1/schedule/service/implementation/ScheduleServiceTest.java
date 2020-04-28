package com.analuciabolico.assembly.v1.schedule.service.implementation;

import com.analuciabolico.assembly.v1.associated.model.Associated;
import com.analuciabolico.assembly.v1.core.BaseUnityTest;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import com.analuciabolico.assembly.v1.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.analuciabolico.assembly.v1.core.Constants.DESCRIPTION;
import static com.analuciabolico.assembly.v1.core.Constants.LOCAL_DATE_END;
import static com.analuciabolico.assembly.v1.core.Constants.LOCAL_DATE_START;
import static com.analuciabolico.assembly.v1.core.Constants.ONE_LONG;
import static com.analuciabolico.assembly.v1.core.Constants.STATUS;
import static com.analuciabolico.assembly.v1.core.Constants.TITLE;
import static com.analuciabolico.assembly.v1.core.validation.GenericMessagesValidationEnum.ENTITY_NOT_FOUND;
import static com.analuciabolico.assembly.v1.core.validation.MessageValidationProperties.getMessage;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

class ScheduleServiceTest extends BaseUnityTest {

    @InjectMocks
    ScheduleService scheduleService;

    @Mock
    ScheduleRepository scheduleRepository;

    @Test
    @DisplayName("Test save")
    void save() {
        doReturn(oneSchedule()).when(scheduleRepository).save(any());
        assertEquals(oneResourceCreated().getId(), scheduleService.save(oneScheduleDto()).getId());
    }

    @Test
    @DisplayName("Test save exception")
    void saveException() {
        doThrow(oneDataIntegrityViolationException()).when(scheduleRepository).save(any());
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> scheduleService.save(oneScheduleDto()));
        assertEquals(PRIMARY_KEY_VIOLATION, exception.getMessage());
    }

    @Test
    @DisplayName("Test findById")
    void findById() {
        doReturn(oneScheduleOptional()).when(scheduleRepository).findById(any());
        Schedule schedule = scheduleService.findById(ONE_LONG);
        assertAll("schedules",
                () -> assertEquals(ONE_LONG, schedule.getId()),
                () -> assertEquals(TITLE, schedule.getTitle()),
                () -> assertEquals(DESCRIPTION, schedule.getDescription()),
                () -> assertEquals(STATUS, schedule.getStatus())
        );
    }

    @Test
    @DisplayName("Test findById exception")
    void findByIdException() {
        doReturn(oneScheduleOptionalEmpty()).when(scheduleRepository).findById(any());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> scheduleService.findById(ONE_LONG));
        assertEquals(getMessage(ENTITY_NOT_FOUND), exception.getMessage());
    }

    @Test
    @DisplayName("Test findAll")
    void findAll() {
        doReturn(oneScheduleList()).when(scheduleRepository).findAll(any(Sort.class));
        List<Schedule> schedules = scheduleService.findAll(oneSortAsc());
        assertAll("schedules",
                () -> assertFalse(schedules.isEmpty()),
                () -> assertEquals(1, schedules.size()),
                () -> assertEquals(ONE_LONG, schedules.get(0).getId()),
                () -> assertEquals(TITLE, schedules.get(0).getTitle()),
                () -> assertEquals(DESCRIPTION, schedules.get(0).getDescription()),
                () -> assertEquals(STATUS, schedules.get(0).getStatus())
        );
    }

    @Test
    @DisplayName("Test findAll no content")
    void findAllNoContent() {
        doReturn(oneScheduleListEmpty()).when(scheduleRepository).findAll(any(Sort.class));
        List<Schedule> schedules = scheduleService.findAll(oneSortAsc());
        assertAll("schedules",
                () -> assertTrue(schedules.isEmpty()),
                () -> assertEquals(0, schedules.size())
        );
    }
}