package com.analuciabolico.assembly.v1.api.vote;

import com.analuciabolico.assembly.v1.core.BaseControllerIT;
import com.analuciabolico.assembly.v1.core.enums.AssociatedVoteApiEnum;
import com.analuciabolico.assembly.v1.core.model.AssociatedVoteApi;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import com.analuciabolico.assembly.v1.vote.model.Vote;
import com.analuciabolico.assembly.v1.vote.repository.VoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static com.analuciabolico.assembly.v1.core.Constants.DESCRIPTION;
import static com.analuciabolico.assembly.v1.core.Constants.ONE_LONG;
import static com.analuciabolico.assembly.v1.core.Constants.TITLE;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.INSERT_SCHEDULE;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.INSERT_SCHEDULE_VOTE;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.INSERT_VOTE;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.REMOVE_SCHEDULE;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.REMOVE_VOTE;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteControllerIT extends BaseControllerIT {

    @Mock
    private VoteRepository voteRepository;

    @Test
    @SqlGroup({
            @Sql(scripts = {INSERT_SCHEDULE_VOTE}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = {REMOVE_SCHEDULE}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    @DisplayName("Save vote")
    @Disabled
    void saveVote() throws Exception {
        doReturn(true).when(associatedVoteFacade).associatedCanVote(any());
        doReturn(new AssociatedVoteApi(AssociatedVoteApiEnum.ABLE_TO_VOTE)).when(associatedVoteClient).ableToVote(any());
        mockMvc.perform(post("/api/v1/vote")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.mapper.writeValueAsString(oneVoteDto())))
                .andExpect(status().isCreated());
        verify(voteRepository, times(1)).save(any());
    }
}