package com.analuciabolico.assembly.v1.core;

public class SqlDocumentProvider {

    public static final String INSERT_LIST_ASSOCIATED = "classpath:db/associated/insert_list.sql";
    public static final String INSERT_LIST_SCHEDULES = "classpath:db/schedule/insert_list.sql";
    public static final String INSERT_LIST_VOTES = "classpath:db/vote/insert_list.sql";

    public static final String INSERT_ASSOCIATED = "classpath:db/associated/insert.sql";
    public static final String INSERT_SCHEDULE = "classpath:db/schedule/insert.sql";
    public static final String INSERT_SCHEDULE_VOTE = "classpath:db/schedule/insert_vote.sql";
    public static final String INSERT_VOTE = "classpath:db/vote/insert.sql";

    public static final String REMOVE_ASSOCIATED = "classpath:db/associated/remove.sql";
    public static final String REMOVE_SCHEDULE = "classpath:db/schedule/remove.sql";
    public static final String REMOVE_VOTE = "classpath:db/vote/remove.sql";
}
