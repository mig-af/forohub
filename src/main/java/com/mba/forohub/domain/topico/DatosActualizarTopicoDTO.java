package com.mba.forohub.domain.topico;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopicoDTO(
    @NotNull(message = "El campo id es obligatorio")
    long id,


    @NotBlank(message = "El campo titulo es obligatorio")
    String titulo,

    String mensaje,

    @NotBlank(message = "El campo nombre_curso es obligatorio")
    @JsonProperty("nombre_curso")
    String curso

) {

}
