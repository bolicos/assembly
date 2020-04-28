package com.analuciabolico.assembly.v1.api.vote;

import com.analuciabolico.assembly.v1.core.BaseControllerIT;
import com.analuciabolico.assembly.v1.vote.repository.VoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static com.analuciabolico.assembly.v1.core.Constants.DESCRIPTION;
import static com.analuciabolico.assembly.v1.core.Constants.ONE_LONG;
import static com.analuciabolico.assembly.v1.core.Constants.TITLE;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.INSERT_VOTE;
import static com.analuciabolico.assembly.v1.core.SqlDocumentProvider.REMOVE_VOTE;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteControllerIT extends BaseControllerIT {

    @Autowired
    private VoteRepository voteRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}