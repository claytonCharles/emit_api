package com.clayton.emit_api.core.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.clayton.emit_api.core.data.dtos.ErrorValidateDTO;
import com.clayton.emit_api.core.data.dtos.ResponseExceptionsDTO;
import com.clayton.emit_api.core.data.dtos.SimpleResponseDTO;

@RestControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseExceptionsDTO> threatMethodArgumentNotValidException(
        MethodArgumentNotValidException exception
    ) {
        List<FieldError> listErrors = exception.getFieldErrors();
        List<ErrorValidateDTO> jsonErros = new ArrayList<ErrorValidateDTO>();
        for (FieldError fieldError : listErrors) {
            ErrorValidateDTO error = new ErrorValidateDTO(fieldError.getField(), fieldError.getDefaultMessage());
            jsonErros.add(error);
        }

        ResponseExceptionsDTO responseException = new ResponseExceptionsDTO(
            "Can't continue proccess, because have errors in you request body!",
            jsonErros
        );
        return ResponseEntity.badRequest().body(responseException);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<SimpleResponseDTO> threatInvalidDataAccessApiUsageException(
        InvalidDataAccessApiUsageException exception
    ) {
        SimpleResponseDTO responseException = new SimpleResponseDTO(exception.getMessage(), false);
        return ResponseEntity.badRequest().body(responseException);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SimpleResponseDTO> threatGeneralException(Exception exception) {
        SimpleResponseDTO responseException = new SimpleResponseDTO(exception.getMessage(), false);
        return ResponseEntity.badRequest().body(responseException);
    }
}