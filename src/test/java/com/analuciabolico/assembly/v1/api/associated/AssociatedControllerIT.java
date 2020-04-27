package com.analuciabolico.assembly.v1.api.associated;

import com.analuciabolico.assembly.v1.associated.dto.AssociatedDto;
import com.analuciabolico.assembly.v1.associated.model.Associated;
import com.analuciabolico.assembly.v1.associated.repository.AssociatedRepository;
import com.analuciabolico.assembly.v1.core.BaseControllerIT;
import io.micrometer.core.instrument.util.JsonUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static com.analuciabolico.assembly.v1.core.Constants.*;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AssociatedControllerIT extends BaseControllerIT {

    @Autowired
    private AssociatedRepository associatedRepository;

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

    @Test
    @DisplayName("Save Associated")
    void saveAssociated() throws Exception {
        AssociatedDto dto = new AssociatedDto(NAME, VALID_CPF);
        mockMvc.perform(post("/api/v1/associated")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        Associated associated = associatedRepository.findByCpf(VALID_CPF);
        assertEquals(dto.getCpf(), associated.getCpf());
    }

}