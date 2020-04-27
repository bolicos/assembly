package com.analuciabolico.assembly.v1.api.associated;

import com.analuciabolico.assembly.v1.core.BaseControllerIT;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static com.analuciabolico.assembly.v1.core.Constants.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AssociatedControllerIT extends BaseControllerIT {

    @Test
    @Sql(
    scripts = "/data/associated.sql",
    executionPhase = BEFORE_TEST_METHOD,
    config = @SqlConfig(transactionMode = ISOLATED)
    )
    @DisplayName("Find Associated By Id")
    @Disabled
    void findAssociatedById() throws Exception {
        mockMvc.perform(get("/api/v1/associated/" + ONE_LONG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", containsString (ONE_STRING)))
                .andExpect(jsonPath("$.name", containsString (NAME)))
                .andExpect(jsonPath("$.cpf", containsString (VALID_CPF)))
                .andExpect(jsonPath("$.createdAt", containsString (CREATED_AT)));
    }

}