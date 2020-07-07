package com.szachnowicz.exchange.rest;

import com.szachnowicz.exchange.dto.error.BusinessException;
import com.szachnowicz.exchange.dto.error.ErrorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ValidationException;

import static com.szachnowicz.exchange.dto.error.BusinessErrorCodes.VALIDATION_EXCEPTION;

@RequiredArgsConstructor
@ControllerAdvice
class RestExceptionHandler {

    public @ResponseBody
    @ExceptionHandler(BusinessException.class)
    ErrorDTO handlBusinessException(BusinessException exception) {
        return new ErrorDTO(exception.getErrorCode(), exception.getMessage());
    }

    public @ResponseBody
    @ExceptionHandler(ValidationException.class)
    ErrorDTO handleValidationException(ValidationException exception) {
        return new ErrorDTO(VALIDATION_EXCEPTION, exception.getMessage());
    }

    public @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ErrorDTO handleValidationException(MethodArgumentNotValidException exception) {
        return new ErrorDTO(VALIDATION_EXCEPTION, printMessage(exception));
    }


    public @ResponseBody
    @ExceptionHandler(Exception.class)
    ErrorDTO handleException(Exception exception) {
        return new ErrorDTO(VALIDATION_EXCEPTION, exception.getCause().getLocalizedMessage());
    }


    private String printMessage(MethodArgumentNotValidException exception) {
        FieldError fieldError = exception.getBindingResult().getFieldError();
        return String.format(" %s  %s ", fieldError.getField(), fieldError.getDefaultMessage());
    }

}

