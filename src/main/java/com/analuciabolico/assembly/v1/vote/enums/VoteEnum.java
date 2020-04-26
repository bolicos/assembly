package com.analuciabolico.assembly.v1.vote.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VoteEnum {
    YES(true),
    NOT(false);

    boolean key;
}
