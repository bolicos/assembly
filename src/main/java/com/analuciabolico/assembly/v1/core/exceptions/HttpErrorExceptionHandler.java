package com.analuciabolico.assembly.v1.core.exceptions;

import com.analuciabolico.assembly.v1.core.exceptions.model.DomainBusinessException;
import com.analuciabolico.assembly.v1.core.exceptions.model.InvalidOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
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

import static com.analuciabolico.assembly.v1.core.validation.GenericMessagesValidationEnum.GENERIC_ERROR;
import static com.analuciabolico.assembly.v1.core.validation.MessageValidationProperties.getMessage;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class HttpErrorExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpErrorExceptionHandler.class);

    private final String genericError = getMessage(GENERIC_ERROR);

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DomainBusinessException.class)
    @ResponseBody
    public ApiError errorBusiness(DomainBusinessException e) {
        LOGGER.error("ERRO DE NEGOCIO: " + e.message, e);
        return ApiError.fromHttpError(UNPROCESSABLE_ENTITY, e);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    @ResponseBody
    public ApiError errorResourceNotFound(RuntimeException e) {
        LOGGER.error("ERRO DE RECURSO NAO ENCONTRADO: " + e.getMessage(), e);
        return ApiError.fromHttpError(NOT_FOUND, e);
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiError errorUnexpected(Exception e) {
        LOGGER.error("ERRO INESPERADO: " + e.getMessage(), e);
        return ApiError.fromMessage(INTERNAL_SERVER_ERROR, genericError);
    }

    @ResponseStatus(FORBIDDEN)
    @ResponseBody
    public ApiError errorForbidden(Exception e) {
        LOGGER.error("ERRO DE ENDPOINT PROIBIDO: " + e.getMessage(), e);
        return ApiError.fromMessage(FORBIDDEN, e.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidOperationException.class)
    @ResponseBody
    public ApiError errorInvalidOperation(InvalidOperationException e) {
        LOGGER.error("ERRO DE OPERACAO INVALIDA: " + e.getMessage(), e);
        return ApiError.fromMessage(BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ApiError errorConstraints(DataIntegrityViolationException e) {
        LOGGER.error("ERRO DE VIOLACAO DE CONSTRAINTS: " + e.getMessage(), e);
        return ApiError.fromMessage(UNPROCESSABLE_ENTITY, e.getMessage());
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