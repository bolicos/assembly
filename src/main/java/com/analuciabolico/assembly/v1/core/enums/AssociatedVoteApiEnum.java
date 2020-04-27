package com.analuciabolico.assembly.v1.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AssociatedVoteApiEnum {
    ABLE_TO_VOTE("Able to vote"),
    UNABLE_TO_VOTE("Unable to vote");

    String key;
}
