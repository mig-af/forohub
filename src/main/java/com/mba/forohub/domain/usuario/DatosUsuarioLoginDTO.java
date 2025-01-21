package com.mba.forohub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosUsuarioLoginDTO(
    @Email
    @NotBlank
    String email,

    @NotBlank
    String password
) {

}
