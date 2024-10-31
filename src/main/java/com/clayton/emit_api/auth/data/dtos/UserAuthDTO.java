package com.clayton.emit_api.auth.data.dtos;

import jakarta.validation.constraints.NotEmpty;

public record UserAuthDTO(
    @NotEmpty(message = "O E-mail não pode ser vazio.")
    String mail,
    @NotEmpty(message = "A Senha não pode ser vazia.")
    String password
) {
}