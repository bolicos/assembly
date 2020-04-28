package com.analuciabolico.assembly.v1.api.schedule;

import com.analuciabolico.assembly.v1.core.BaseControllerIT;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import com.analuciabolico.assembly.v1.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static com.analuciabolico.assembly.v1.core.Constants.*;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.INSERT_LIST_SCHEDULES;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.INSERT_SCHEDULE;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.REMOVE_SCHEDULE;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ScheduleControllerIT extends BaseControllerIT {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    @SqlGroup({
            @Sql(scripts = {REMOVE_SCHEDULE}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = {INSERT_SCHEDULE}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = {REMOVE_SCHEDULE}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })

    @DisplayName("Find Schedule By Id")
    void findScheduleById() throws Exception {
        mockMvc.perform(get("/api/v1/schedules/" + ONE_LONG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", containsString(TITLE)))
                .andExpect(jsonPath("$.description", containsString(DESCRIPTION)));
    }

    @Test
    @DisplayName("Find Schedule Not Exist")
    void findScheduleNotExist() throws Exception {
        mockMvc.perform(get("/api/v1/schedules/" + NONEXISTENT_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Find Schedule Invalid Parameter")
    void findScheduleInvalidParameter() throws Exception {
        mockMvc.perform(get("/api/v1/schedules/" + INVALID_ID))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Save Schedule")
    void saveSchedule() throws Exception {
        mockMvc.perform(post("/api/v1/schedules")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.mapper.writeValueAsString(oneScheduleDto())))
                .andExpect(status().isCreated());

        Schedule schedule = scheduleRepository.findByTitle(TITLE);
        assertEquals(oneScheduleDto().getTitle(), schedule.getTitle());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = {REMOVE_SCHEDULE}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = {INSERT_LIST_SCHEDULES}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = {REMOVE_SCHEDULE}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    @DisplayName("Find all Schedules")
    void findAllSchedules() throws Exception {
        mockMvc.perform(get("/api/v1/schedules/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id", notNullValue()))
                .andExpect(jsonPath("$.[*].id", containsInAnyOrder(LIST_INTEGER)))
                .andExpect(jsonPath("$.[*].title", containsInAnyOrder(LIST_TITLES)))
                .andExpect(jsonPath("$.[*].description", containsInAnyOrder(LIST_DESCRIPTIONS)));
    }
}