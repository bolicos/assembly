package com.analuciabolico.assembly.v1.api.associated;

import com.analuciabolico.assembly.v1.associated.model.Associated;
import com.analuciabolico.assembly.v1.associated.repository.AssociatedRepository;
import com.analuciabolico.assembly.v1.core.BaseControllerIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static com.analuciabolico.assembly.v1.core.Constants.INVALID_ID;
import static com.analuciabolico.assembly.v1.core.Constants.NAME;
import static com.analuciabolico.assembly.v1.core.Constants.NONEXISTENT_ID;
import static com.analuciabolico.assembly.v1.core.Constants.ONE_LONG;
import static com.analuciabolico.assembly.v1.core.Constants.VALID_CPF;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.INSERT_ASSOCIATED;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.REMOVE_ASSOCIATED;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AssociatedControllerIT extends BaseControllerIT {

    @Autowired
    private AssociatedRepository associatedRepository;

    @Test
    @SqlGroup({
            @Sql(scripts = {REMOVE_ASSOCIATED}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = {INSERT_ASSOCIATED}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = {REMOVE_ASSOCIATED}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    @DisplayName("Find associated by id")
    void findAssociatedById() throws Exception {
        mockMvc.perform(get("/api/v1/associated/" + ONE_LONG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", containsString(NAME)))
                .andExpect(jsonPath("$.cpf", containsString(VALID_CPF)));
    }

    @Test
    @DisplayName("Find associated not exist")
    void findAssociatedNotExist() throws Exception {
        mockMvc.perform(get("/api/v1/associated/" + NONEXISTENT_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Find associated invalid parameter")
    void findAssociatedInvalidParameter() throws Exception {
        mockMvc.perform(get("/api/v1/associated/" + INVALID_ID))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Save associated")
    void saveAssociated() throws Exception {
        mockMvc.perform(post("/api/v1/associated")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.mapper.writeValueAsString(oneAssociatedDto())))
                .andExpect(status().isCreated());

        Associated associated = associatedRepository.findByCpf(VALID_CPF);
        assertEquals(oneAssociatedDto().getCpf(), associated.getCpf());
    }

    @Test
    @DisplayName("Do not save an associated")
    void DoNotsaveAnAssociated() throws Exception {
        mockMvc.perform(post("/api/v1/associated")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.mapper.writeValueAsString(oneAssociatedDtoInvalid())))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = {INSERT_ASSOCIATED}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = {REMOVE_ASSOCIATED}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    @DisplayName("Save associated with existing CPF")
    void saveAssociatedExistingCpf() throws Exception {
        mockMvc.perform(post("/api/v1/associated")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.mapper.writeValueAsString(oneAssociatedDto())))
                .andExpect(status().isUnprocessableEntity());
    }

}