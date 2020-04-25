package com.analuciabolico.assembly.v1.core.exceptions;

import com.analuciabolico.assembly.v1.core.exceptions.model.DomainBusinessException;
import com.analuciabolico.assembly.v1.core.exceptions.model.InvalidOperationException;
import com.analuciabolico.assembly.v1.core.validation.GenericMessagesValidationEnum;
import com.analuciabolico.assembly.v1.core.validation.MessageValidationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.HandlerMapping;

import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
public class HttpErrorExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpErrorExceptionHandler.class);

    private final String genericError = MessageValidationProperties.getMensagem(GenericMessagesValidationEnum.ERRO_GENERICO);

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DomainBusinessException.class)
    @ResponseBody
    public ApiError errorBusiness(DomainBusinessException e) {
        LOGGER.error("ERRO DE NEGÓCIO: " + e.message, e);
        return ApiError.fromHttpError(UNPROCESSABLE_ENTITY, e);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    @ResponseBody
    public ApiError errorResourceNotFound(RuntimeException e) {
        LOGGER.error("ERRO DE RECURSO NÃO ENCONTRADO: " + e.getMessage(), e);
        return ApiError.fromHttpError(NOT_FOUND, e);
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiError errorUnexpected(Exception e) {
        LOGGER.error("ERRO INESPERADO: " + e.getMessage(), e);
        return ApiError.fromMessage(INTERNAL_SERVER_ERROR, genericError);
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ApiError errorUnauthorized(AccessDeniedException e) {
        LOGGER.error("ERRO DE NÃO AUTORIZADO: " + e.getMessage(), e);
        return ApiError.fromMessage(UNAUTHORIZED, e.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidOperationException.class)
    @ResponseBody
    public ApiError errorInvalidOperation(InvalidOperationException e) {
        LOGGER.error("ERRO DE OPERACAO INVALIDA: " + e.getMessage(), e);
        return ApiError.fromMessage(BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<?> errorInvalidParameter(MethodArgumentTypeMismatchException e, WebRequest request) {
        LOGGER.error("ERRO DE PARAMETRO INVALIDO NA REQUEST: " + e.getMessage(), e);
        boolean isEnum = Objects.requireNonNull(e.getRequiredType()).isEnum();
        String nomeParametro = e.getName();

        return (isEnum && existParameterInPathVariable(request, nomeParametro)) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.badRequest().body(ApiError.fromMessage(BAD_REQUEST, e.getMessage()));
    }

    private boolean existParameterInPathVariable(WebRequest request, String nameParameter) {
        Map<?, ?> variables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, 0);
        return Objects.requireNonNull(variables).containsKey(nameParameter);
    }

}