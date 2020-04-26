package com.analuciabolico.assembly.v1.vote.service.interfaces;

import com.analuciabolico.assembly.v1.core.model.ResourceCreated;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import com.analuciabolico.assembly.v1.vote.dto.VoteDto;
import com.analuciabolico.assembly.v1.vote.enums.VoteEnum;

import java.util.Map;

public interface IVoteService {
    ResourceCreated save(VoteDto voteDto);

    Map<VoteEnum, Long> countVotes(Schedule schedule);
}
