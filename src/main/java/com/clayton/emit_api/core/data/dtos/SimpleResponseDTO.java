package com.clayton.emit_api.core.data.dtos;

/**
 * DTO simples para funções que possuem apenas uma string de retorno.
 * @author Clayton Charles
 * @version 0.1.0
 */
public record SimpleResponseDTO(
    String message,
    boolean success
) {
}