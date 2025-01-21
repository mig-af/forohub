package com.mba.forohub.domain.topico;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopicoDTO(

    
    @JsonProperty("id_usuario")
    long idUsuario,

    @NotBlank(message = "El campo titulo es obligatorio")

    String titulo,

    @NotBlank(message = "El campo mensaje es obligatorio")
    String mensaje,

    @NotBlank(
        message = "El campo nombre_curso es obligatorio"
    )
    @JsonProperty("nombre_curso")
    String curso 
) {

}
