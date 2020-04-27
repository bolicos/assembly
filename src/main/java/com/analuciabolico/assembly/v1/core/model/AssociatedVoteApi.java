package com.analuciabolico.assembly.v1.core.model;

import com.analuciabolico.assembly.v1.core.enums.AssociatedVoteApiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AssociatedVoteApi implements Serializable {
    private AssociatedVoteApiEnum status;
}
