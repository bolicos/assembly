package com.analuciabolico.assembly.v1.schedule.service.implementation;

import com.analuciabolico.assembly.v1.core.model.ResourceCreated;
import com.analuciabolico.assembly.v1.messaging.Publisher;
import com.analuciabolico.assembly.v1.schedule.dto.ScheduleDto;
import com.analuciabolico.assembly.v1.schedule.dto.ScheduleResultDto;
import com.analuciabolico.assembly.v1.schedule.dto.ScheduleSessionDto;
import com.analuciabolico.assembly.v1.schedule.enums.ScheduleResultEnum;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import com.analuciabolico.assembly.v1.schedule.repository.ScheduleRepository;
import com.analuciabolico.assembly.v1.schedule.service.interfaces.IScheduleService;
import com.analuciabolico.assembly.v1.vote.enums.VoteEnum;
import com.analuciabolico.assembly.v1.vote.service.interfaces.IVoteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

import static com.analuciabolico.assembly.v1.core.validation.GenericMessagesValidationEnum.ENTITY_NOT_FOUND;
import static com.analuciabolico.assembly.v1.core.validation.MessageValidationProperties.getMessage;
import static com.analuciabolico.assembly.v1.schedule.enums.ScheduleStatusEnum.OPEN;

@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final IVoteService voteService;
    private final Publisher publisher;

    @Override
    public ResourceCreated save(ScheduleDto scheduleDto) {
        return new ResourceCreated(scheduleRepository.save(scheduleDto.convertToSchedule()).getId());
    }

    @Override
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(getMessage(ENTITY_NOT_FOUND)));
    }

    @Override
    public List<Schedule> findAll(Sort sort) {
        return scheduleRepository.findAll(sort);
    }

    @Override
    public Schedule session(ScheduleSessionDto scheduleSessionDto, Long id) {
        ScheduleSessionDto scheduleSession = scheduleSessionDto == null ?
                new ScheduleSessionDto().convert() :
                scheduleSessionDto.convert();
        Schedule schedule = scheduleRepository.getOne(id);
        schedule.updateStatus(OPEN);
        schedule.addSession(scheduleSession.getStartTime(), scheduleSession.getEndTime());
        return scheduleRepository.save(schedule);
    }

    @Override
    public ScheduleResultEnum result(ScheduleResultDto status, Long id) {
        Schedule schedule = scheduleRepository.getOne(id);
        Map<VoteEnum, Long> votes = voteService.countVotes(schedule);
        schedule.calculateResult(votes);
        schedule.updateStatus(status.getStatus());
        schedule.updateDuration();
        scheduleRepository.save(schedule);
        publisher.sendTopic(schedule);
        return schedule.getResult();
    }
}
