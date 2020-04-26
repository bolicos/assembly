package com.analuciabolico.assembly.v1.schedule.repository;

import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
