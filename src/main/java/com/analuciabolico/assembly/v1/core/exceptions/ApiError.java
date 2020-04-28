package com.analuciabolico.assembly.v1.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
public class ApiError implements Serializable {
    public Integer statusCode;
    public String error;
    public List<String> messages;

    private ApiError(Integer statusCode, String error, String messages) {
        this.statusCode = statusCode;
        this.error = error;
        this.messages = (messages == null || messages.isEmpty()) ?
                Collections.emptyList() :  Collections.singletonList(messages);
    }

    public static ApiError fromHttpError(HttpStatus httpStatus, Exception exception) {
        return new ApiError(httpStatus.value(), httpStatus.getReasonPhrase(), exception.getMessage());
    }

    public static ApiError fromMessage(HttpStatus httpStatus, String message) {
        return new ApiError(httpStatus.value(), httpStatus.getReasonPhrase(), message);
    }

    public static ApiError fromBindingResult(BindingResult bindingResult) {
        List<String> erros = bindingResult
                .getAllErrors()
                .stream()
                .map(error -> {
                    FieldError fieldError = (FieldError) error;
                    return fieldError.getField() + " : " + fieldError.getDefaultMessage();
                }).collect(Collectors.toList());

        return new ApiError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(),
                erros
        );
    }
}