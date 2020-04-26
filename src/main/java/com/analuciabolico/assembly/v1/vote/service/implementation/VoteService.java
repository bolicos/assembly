package com.analuciabolico.assembly.v1.vote.service.implementation;

import com.analuciabolico.assembly.v1.associated.model.Associated;
import com.analuciabolico.assembly.v1.associated.repository.AssociatedRepository;
import com.analuciabolico.assembly.v1.core.exceptions.model.InvalidOperationException;
import com.analuciabolico.assembly.v1.core.model.ResourceCreated;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import com.analuciabolico.assembly.v1.schedule.repository.ScheduleRepository;
import com.analuciabolico.assembly.v1.vote.dto.VoteDto;
import com.analuciabolico.assembly.v1.vote.enums.VoteEnum;
import com.analuciabolico.assembly.v1.vote.model.Vote;
import com.analuciabolico.assembly.v1.vote.repository.VoteRepository;
import com.analuciabolico.assembly.v1.vote.service.interfaces.IVoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

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
        Associated associated = associatedRepository.findByCpf(voteDto.getCpf());
        Schedule schedule = scheduleRepository.getOne(voteDto.getSchedule());
        Optional<Vote> vote = voteRepository.findByScheduleAndAssociated(schedule, associated);
        boolean notExists = vote.isEmpty();
        if (schedule.getStatus() == OPEN &&
                schedule.getEndTime().isAfter(LocalDateTime.now()) &&
                    notExists) {
            return new ResourceCreated(voteRepository.save(voteDto.convertToVote(associated.getId())).getId());
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
