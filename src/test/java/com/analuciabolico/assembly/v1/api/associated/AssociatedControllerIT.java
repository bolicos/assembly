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

import static com.analuciabolico.assembly.v1.core.Constants.*;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.INSERT_ASSOCIATED;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.REMOVE_ASSOCIATED;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AssociatedControllerIT extends BaseControllerIT {

    @Autowired
    private AssociatedRepository associatedRepository;

    @Test
    @SqlGroup({
            @Sql(scripts = { INSERT_ASSOCIATED }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = { REMOVE_ASSOCIATED }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    @DisplayName("Find Associated By Id")
    void findAssociatedById() throws Exception {
        mockMvc.perform(get("/api/v1/associated/" + ONE_LONG))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ONE_INTEGER))
                .andExpect(jsonPath("$.name", containsString (NAME)))
                .andExpect(jsonPath("$.cpf", containsString (VALID_CPF)));
    }

    @Test
    @DisplayName("Find Associated Not Exist")
    void findAssociatedNotExist() throws Exception {
        mockMvc.perform(get("/api/v1/associated/" + NONEXISTENT_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Find Associated Invalid Parameter")
    void findAssociatedInvalidParameter() throws Exception {
        mockMvc.perform(get("/api/v1/associated/" + INVALID_ID))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Save Associated")
    void saveAssociated() throws Exception {
        mockMvc.perform(post("/api/v1/associated")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.mapper.writeValueAsString(oneAssociatedDto())))
                .andExpect(status().isCreated());

        Associated associated = associatedRepository.findByCpf(VALID_CPF);
        assertEquals(oneAssociatedDto().getCpf(), associated.getCpf());
    }

    @Test
    @DisplayName("Save Associated With Existing CPF")
    void saveAssociatedExistingCpf() throws Exception {
        mockMvc.perform(post("/api/v1/associated")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.mapper.writeValueAsString(oneAssociatedDto())))
                .andExpect(status().isUnprocessableEntity());

        verify(associatedRepository, times(1)).findByCpf(any());
    }

}