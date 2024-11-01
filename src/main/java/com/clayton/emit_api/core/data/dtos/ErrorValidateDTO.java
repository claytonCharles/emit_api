package com.clayton.emit_api.core.data.dtos;

/**
 * DTO para formação do JSON de campos com erro no formulário enviado.
 * @author Clayton Charles
 * @version 0.1.0
 */
public record ErrorValidateDTO(
    String field,
    String message
) {   
}