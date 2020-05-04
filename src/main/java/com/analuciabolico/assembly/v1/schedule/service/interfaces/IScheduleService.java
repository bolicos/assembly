package com.analuciabolico.assembly.v1.schedule.service.interfaces;

import com.analuciabolico.assembly.v1.core.model.ResourceCreated;
import com.analuciabolico.assembly.v1.schedule.dto.ScheduleDto;
import com.analuciabolico.assembly.v1.schedule.dto.ScheduleResultDto;
import com.analuciabolico.assembly.v1.schedule.dto.ScheduleSessionDto;
import com.analuciabolico.assembly.v1.schedule.enums.ScheduleResultEnum;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IScheduleService {

    ResourceCreated save(ScheduleDto scheduleDto);

    Schedule findById(Long id);

    List<Schedule> findAll(Sort sort);

    Schedule session(ScheduleSessionDto scheduleSessionDto, Long id) throws InterruptedException;

    ScheduleResultEnum result(ScheduleResultDto status, Long id);
}
