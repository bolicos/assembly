package com.analuciabolico.assembly.v1.api.schedule;

import com.analuciabolico.assembly.v1.core.BaseControllerIT;
import com.analuciabolico.assembly.v1.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static com.analuciabolico.assembly.v1.core.Constants.DESCRIPTION;
import static com.analuciabolico.assembly.v1.core.Constants.NONEXISTENT_ID;
import static com.analuciabolico.assembly.v1.core.Constants.ONE_LONG;
import static com.analuciabolico.assembly.v1.core.Constants.TITLE;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.INSERT_SCHEDULE;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.REMOVE_SCHEDULE;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        mockMvc.perform(get("/api/v1/schedule/" + ONE_LONG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", containsString(TITLE)))
                .andExpect(jsonPath("$.description", containsString(DESCRIPTION)));
    }

    @Test
    @DisplayName("Find Schedule Not Exist")
    void findScheduleNotExist() throws Exception {
        mockMvc.perform(get("/api/v1/schedule/" + NONEXISTENT_ID))
                .andExpect(status().isNotFound());
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}