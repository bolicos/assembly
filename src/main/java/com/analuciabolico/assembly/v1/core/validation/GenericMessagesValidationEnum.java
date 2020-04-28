package com.analuciabolico.assembly.v1.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenericMessagesValidationEnum {
    REQUIRED_FIELD("javax.validation.constraints.NotNull.message"),
    MAXIMUM_SIZE("javax.validation.constraints.Size.message"),
    EQUAL_SIZE("javax.validation.constraints.Size.equal.message"),
    INVALID_EMAIL("invalidEmail.message"),
    INVALID_CPF("invalidCpf.message"),
    GENERIC_ERROR("genericError.message"),
    SESSION_ALREADY_EXISTS("sessionAlreadyExists.message"),
    SESSION_STATUS_NOT_FOUND("sessionNotFound.message"),
    SESSION_STATUS_NOT_UPDATE("sessionNotUpdate.message"),
    NOT_POSSIBLE_VOTE("notPossibleVote.message"),
    RESULTS_ALREADY_CALCULATED("resultsAlreadyCalculated.message"),
    ENTITY_NOT_FOUND("entityNotFound.message"),
    ERROR_CONVERT_NUMBER("errorConvertNumber.message");

    private String key;
}
