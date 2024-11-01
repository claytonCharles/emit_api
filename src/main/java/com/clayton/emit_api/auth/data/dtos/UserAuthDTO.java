package com.clayton.emit_api.auth.data.dtos;

import jakarta.validation.constraints.NotEmpty;

/**
 * DTO usado como formulário para a requisição de autenticação no sistema.
 * @author Clayton Charles
 * @version 0.1.0
 */
public record UserAuthDTO(
    @NotEmpty(message = "O E-mail não pode ser vazio.")
    String mail,
    @NotEmpty(message = "A Senha não pode ser vazia.")
    String password
) {
}