package com.mba.forohub.domain.autor;
import com.mba.forohub.domain.usuario.DatosUsuarioRegistro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;

    private String password;

    public Autor(DatosUsuarioRegistro datosAutorRegistro){
        this.nombre = datosAutorRegistro.nombre();
        this.apellido = datosAutorRegistro.apellido();
        this.email = datosAutorRegistro.email();
        this.password = datosAutorRegistro.password();
    }

    
}
