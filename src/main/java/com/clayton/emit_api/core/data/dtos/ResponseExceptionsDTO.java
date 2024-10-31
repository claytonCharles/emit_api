package com.clayton.emit_api.core.data.dtos;

public record ResponseExceptionsDTO(
    String message,
    Object errors
) {    
}