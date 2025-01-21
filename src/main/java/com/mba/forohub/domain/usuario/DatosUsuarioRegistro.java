package com.mba.forohub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosUsuarioRegistro(
    @NotBlank( message = "El campo nombre es obligatorio")
    String nombre,
    @NotBlank( message = "El campo apellido es obligatorio")
    String apellido,

    @Email
    @NotBlank( message = "El campo email es obligatorio")
    String email,
    @NotBlank( message = "El campo password es obligatorio")
    String password

) {

}
