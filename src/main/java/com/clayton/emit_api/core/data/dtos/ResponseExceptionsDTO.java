package com.clayton.emit_api.core.data.dtos;

/**
 * DTO simples para retorno da exception de validação de formulário.
 * @author Clayton Charles
 * @version 0.1.0
 */
public record ResponseExceptionsDTO(
    String message,
    Object errors
) {    
}