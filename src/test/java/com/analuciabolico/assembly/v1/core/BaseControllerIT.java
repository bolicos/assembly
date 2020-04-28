package com.analuciabolico.assembly.v1.core;

import com.analuciabolico.assembly.v1.associated.dto.AssociatedDto;
import com.analuciabolico.assembly.v1.associated.repository.AssociatedRepository;
import com.analuciabolico.assembly.v1.core.service.client.IAssociatedVoteClient;
import com.analuciabolico.assembly.v1.core.service.facade.AssociatedVoteFacade;
import com.analuciabolico.assembly.v1.schedule.dto.ScheduleDto;
import com.analuciabolico.assembly.v1.schedule.repository.ScheduleRepository;
import com.analuciabolico.assembly.v1.vote.dto.VoteDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.analuciabolico.assembly.v1.core.Constants.*;
import static com.analuciabolico.assembly.v1.core.Tags.RUN_SLOW;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@Tag(RUN_SLOW)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseControllerIT {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper mapper;

    @Mock
    protected AssociatedRepository associatedRepository;

    @Mock
    protected ScheduleRepository scheduleRepository;

    @Mock
    protected AssociatedVoteFacade associatedVoteFacade;

    @Mock
    protected IAssociatedVoteClient associatedVoteClient;


    protected ScheduleDto oneScheduleDto() {
        return new ScheduleDto(TITLE, DESCRIPTION);
    }

    protected ScheduleDto oneScheduleDtoInvalid() {
        return new ScheduleDto(DESCRIPTION);
    }

    protected AssociatedDto oneAssociatedDto() {
        return new AssociatedDto(NAME, VALID_CPF);
    }

    protected AssociatedDto oneAssociatedDtoInvalid() {
        return new AssociatedDto( VALID_CPF);
    }

    protected VoteDto oneVoteDto() {
            return new VoteDto(VALID_CPF, VOTE , ONE_LONG);
    }

    protected AssociatedDto oneAssociatedDtoCpf() {
        return new AssociatedDto(NAME, VALID_CPF_2);
    }

    protected String oneCpf() {
        return VALID_CPF;
    }

}
