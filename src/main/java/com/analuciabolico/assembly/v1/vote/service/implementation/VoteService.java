package com.analuciabolico.assembly.v1.vote.service.implementation;

import com.analuciabolico.assembly.v1.associated.repository.AssociatedRepository;
import com.analuciabolico.assembly.v1.core.exceptions.model.InvalidOperationException;
import com.analuciabolico.assembly.v1.core.model.ResourceCreated;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import com.analuciabolico.assembly.v1.schedule.repository.ScheduleRepository;
import com.analuciabolico.assembly.v1.vote.dto.VoteDto;
import com.analuciabolico.assembly.v1.vote.enums.VoteEnum;
import com.analuciabolico.assembly.v1.vote.repository.VoteRepository;
import com.analuciabolico.assembly.v1.vote.service.interfaces.IVoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

import static com.analuciabolico.assembly.v1.core.validation.GenericMessagesValidationEnum.NOT_POSSIBLE_VOTE;
import static com.analuciabolico.assembly.v1.core.validation.MessageValidationProperties.getMessage;
import static com.analuciabolico.assembly.v1.schedule.enums.ScheduleStatusEnum.OPEN;
import static com.analuciabolico.assembly.v1.vote.enums.VoteEnum.NOT;
import static com.analuciabolico.assembly.v1.vote.enums.VoteEnum.YES;

@Service
@AllArgsConstructor
public class VoteService implements IVoteService {

    private final VoteRepository voteRepository;
    private final AssociatedRepository associatedRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public ResourceCreated save(VoteDto voteDto) {
        Long id = associatedRepository.findByCpf(voteDto.getCpf()).getId();
        Schedule schedule = scheduleRepository.getOne(voteDto.getSchedule());
        Schedule schedule = scheduleRepository.get(voteDto.getSchedule());
        if (schedule.getStatus() == OPEN &&
                schedule.getEndTime().isAfter(LocalDateTime.now()) &&
                    ) {
            return new ResourceCreated(voteRepository.save(voteDto.convertToVote(id)).getId());
        } else {
            throw new InvalidOperationException(getMessage(NOT_POSSIBLE_VOTE));
        }

    }

    @Override
    public Map<VoteEnum, Long> countVotes(Schedule schedule) {
        Long yes = voteRepository.findBySchedule(schedule).stream().filter(vote -> vote.getValueEnum() == YES).count();
        Long not = voteRepository.findBySchedule(schedule).stream().filter(vote -> vote.getValueEnum() == NOT).count();
        return Map.of(YES, yes, NOT, not);
    }

}
