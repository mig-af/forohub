package com.mba.forohub.domain.topico;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.mba.forohub.domain.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity 
@Table(name = "topico")
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String mensaje;
    private String fechaCreacion;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario autor;
    
    private String curso;


    public Topico(DatosRegistroTopicoDTO datosRegistroTopico){
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaCreacion = obtenerFechaHoraActual();
        this.status = true;
        this.curso = datosRegistroTopico.curso();
    }

    

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }


    public Topico actualizarDatos(DatosActualizarTopicoDTO datosActualizarTopicoDTO){
        this.titulo = datosActualizarTopicoDTO.titulo();
        this.mensaje = datosActualizarTopicoDTO.mensaje();
        this.curso = datosActualizarTopicoDTO.curso();
        this.fechaCreacion = obtenerFechaHoraActual();

        return this;
    }


    private String obtenerFechaHoraActual(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return time.format(formatter);
    }


    public void cambiarEstado(){
        this.status = false;
    }





}
