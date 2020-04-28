package com.analuciabolico.assembly.v1.core;

import com.analuciabolico.assembly.v1.associated.dto.AssociatedDto;
import com.analuciabolico.assembly.v1.associated.repository.AssociatedRepository;
import com.analuciabolico.assembly.v1.schedule.dto.ScheduleDto;
import com.analuciabolico.assembly.v1.schedule.repository.ScheduleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.analuciabolico.assembly.v1.core.Constants.DESCRIPTION;
import static com.analuciabolico.assembly.v1.core.Constants.NAME;
import static com.analuciabolico.assembly.v1.core.Constants.TITLE;
import static com.analuciabolico.assembly.v1.core.Constants.VALID_CPF;
import static com.analuciabolico.assembly.v1.core.Constants.VALID_CPF_2;
import static com.analuciabolico.assembly.v1.core.Tags.RUN_SLOW;

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

    @Autowired
    protected AssociatedRepository associatedRepository;

    @Autowired
    protected ScheduleRepository scheduleRepository;

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


    protected AssociatedDto oneAssociatedDtoCpf() {
        return new AssociatedDto(NAME, VALID_CPF_2);
    }

    protected String oneCpf() {
        return VALID_CPF;
    }

}
