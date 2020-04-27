package com.analuciabolico.assembly.v1.schedule.service.implementation;

import com.analuciabolico.assembly.v1.core.BaseUnityTest;
import com.analuciabolico.assembly.v1.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

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
}