package com.analuciabolico.assembly.v1.core;

import com.analuciabolico.assembly.v1.schedule.enums.ScheduleStatusEnum;
import com.analuciabolico.assembly.v1.vote.enums.VoteEnum;
import com.analuciabolico.assembly.v1.vote.model.Vote;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

public class Constants {

    public static final String ONE_STRING = "1";
    public static final String NAME = "Ursula Burns";
    public static final String VALID_CPF = "22191757073";
    public static final String VALID_CPF_2 = "76086743002";
    public static final String[] LIST_NAMES = {"Ursula Burns", "Maria Antonieta", "Afonsa Meireles"};
    public static final String[] LIST_VALID_CPFS = {"22191757073", "67721526035", "56859521040"};
    public static final String CREATED_AT_STRING = "2020-04-26T18:55:30";
    public static final LocalDateTime CREATED_AT = LocalDateTime.of(2020, Month.APRIL, 26, 15, 2, 0);

    public static final Long ONE_LONG = 1L;
    public static final Long NONEXISTENT_ID = 99L;
    public static final String INVALID_ID = "21R";
    public static final Integer ONE_INTEGER = 1;
    public static final Integer[] LIST_INTEGER = {1, 2, 3};
    public static final String[] LIST_TITLES = {"Pauta sobre a igualdade", "Pauta sobre a diversidade", "Pauta sobre programação"};
    public static final String[] LIST_DESCRIPTIONS = {"Nesta pauta iremos abordar assuntos sobre a igualdade entre as pessoas", "Nesta pauta iremos abordar assuntos sobre a diversidade", "Nesta pauta iremos abordar assuntos sobre programação"};
    public static final String TITLE = "Pauta sobre a igualdade";
    public static final String DESCRIPTION = "Nesta pauta iremos abordar assuntos sobre a igualdade entre as pessoas";
    public static final ScheduleStatusEnum STATUS = ScheduleStatusEnum.CREATED;
    public static final LocalDateTime LOCAL_DATE_START = LocalDateTime.of(2020, Month.APRIL, 26, 15, 1, 0);
    public static final LocalDateTime LOCAL_DATE_END = LocalDateTime.of(2020, Month.APRIL, 26, 15, 2, 0);

    public static final VoteEnum VOTE = VoteEnum.YES;


}
