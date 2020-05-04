package com.analuciabolico.assembly.v1.schedule.component.thread;

import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import com.analuciabolico.assembly.v1.schedule.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.EntityNotFoundException;

import static com.analuciabolico.assembly.v1.core.validation.GenericMessagesValidationEnum.ENTITY_NOT_FOUND;
import static com.analuciabolico.assembly.v1.core.validation.MessageValidationProperties.getMessage;
import static com.analuciabolico.assembly.v1.schedule.enums.ScheduleStatusEnum.CLOSE;

@Setter
@AllArgsConstructor
public class ThreadScheduleComponent implements Runnable {

    private Long id;
    private final ScheduleRepository scheduleRepository;

    @Override
    @Scheduled()
    public void run() {
        Schedule schedule = scheduleRepository.findById(this.id).orElseThrow(
                () -> new EntityNotFoundException(getMessage(ENTITY_NOT_FOUND)));
        schedule.updateStatus(CLOSE);
        scheduleRepository.save(schedule);
    }
}
