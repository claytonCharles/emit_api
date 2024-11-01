package com.clayton.emit_api.auth.data.dtos;

/**
 * DTO simples para retorno do JWT em formato JSON.
 * @author Clayton Charles
 * @version 0.1.0
 */
public record ResponseAuthDTO(
    String token
) {
}
