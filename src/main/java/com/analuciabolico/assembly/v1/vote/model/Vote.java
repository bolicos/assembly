package com.analuciabolico.assembly.v1.vote.model;

import com.analuciabolico.assembly.v1.associated.model.Associated;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import com.analuciabolico.assembly.v1.vote.enums.VoteEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("schedule")
@SequenceGenerator(name = "SEQ_VOTE", sequenceName = "SEQUENCE_VOTE", allocationSize = 1)
public class Vote implements Serializable {

    @Id
    @Column(name = "UID_VOTE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VOTE")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "FK_ASSOCIATED_UID")
    private Associated associated;

    @Column(nullable = false)
    private LocalDateTime voteTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private VoteEnum valueEnum;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "FK_SCHEDULE_UID")
    private Schedule schedule;
}
