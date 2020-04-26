package com.analuciabolico.assembly.v1.vote.dto;

import com.analuciabolico.assembly.v1.associated.model.Associated;
import com.analuciabolico.assembly.v1.schedule.model.Schedule;
import com.analuciabolico.assembly.v1.vote.enums.VoteEnum;
import com.analuciabolico.assembly.v1.vote.model.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto implements Serializable {
    private String cpf;
    private VoteEnum vote;
    private Long schedule;

    public Vote convertToVote(Long id){
        return new Vote(null, new Associated(id), LocalDateTime.now(), this.vote, new Schedule(this.schedule));
    }
}
