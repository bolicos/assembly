package com.analuciabolico.assembly.v1.schedule.model;

import com.analuciabolico.assembly.v1.core.exceptions.model.DomainBusinessException;
import com.analuciabolico.assembly.v1.schedule.enums.ScheduleResultEnum;
import com.analuciabolico.assembly.v1.schedule.enums.ScheduleStatusEnum;
import com.analuciabolico.assembly.v1.vote.enums.VoteEnum;
import com.analuciabolico.assembly.v1.vote.model.Vote;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import static com.analuciabolico.assembly.v1.core.validation.GenericMessagesValidationEnum.RESULTS_ALREADY_CALCULATED;
import static com.analuciabolico.assembly.v1.core.validation.GenericMessagesValidationEnum.SESSION_ALREADY_EXISTS;
import static com.analuciabolico.assembly.v1.core.validation.MessageValidationProperties.getMessage;
import static com.analuciabolico.assembly.v1.schedule.enums.ScheduleResultEnum.*;
import static com.analuciabolico.assembly.v1.schedule.enums.ScheduleStatusEnum.CLOSE;
import static com.analuciabolico.assembly.v1.schedule.enums.ScheduleStatusEnum.update;
import static com.analuciabolico.assembly.v1.vote.enums.VoteEnum.NOT;
import static com.analuciabolico.assembly.v1.vote.enums.VoteEnum.YES;

@Table
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SequenceGenerator(name = "SEQ_SCHEDULE", sequenceName = "SEQUENCE_SCHEDULE", allocationSize = 1)
public class Schedule implements Serializable {

    public Schedule(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "UID_SCHEDULE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SCHEDULE")
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ScheduleStatusEnum status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule", fetch = FetchType.EAGER)
    private Set<Vote> votes;

    private Long durationEstimate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ScheduleResultEnum result;

    public void addSession(LocalDateTime startTime, LocalDateTime endTime) {
        if (this.startTime == null && this.endTime == null) {
            this.startTime = startTime;
            this.endTime = endTime;
        } else throw new DomainBusinessException(getMessage(SESSION_ALREADY_EXISTS));
    }

    public void updateStatus(ScheduleStatusEnum status) {
        update(this, status);
    }

    public void calculateResult(Map<VoteEnum, Long> votes) {
        if (this.result == UNDEFINED) {
            this.result = votes.get(YES).equals(votes.get(NOT)) ?
                    DRAW :
                    votesResult(votes);
        } else throw new DomainBusinessException(getMessage(RESULTS_ALREADY_CALCULATED));
    }

    private ScheduleResultEnum votesResult(Map<VoteEnum, Long> votes) {
        return votes.get(YES) > votes.get(NOT) ? APPROVED : DISAPPROVED;
    }

    public void updateDuration() {
        if (this.status == CLOSE) {
            this.durationEstimate = Duration.between(this.endTime, this.startTime).getSeconds() / 60;
        } else throw new DomainBusinessException(getMessage(RESULTS_ALREADY_CALCULATED));
    }


}
