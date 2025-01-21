package com.mba.forohub.controller;

import java.net.URI;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mba.forohub.domain.topico.DatosActualizarTopicoDTO;
import com.mba.forohub.domain.topico.DatosRegistroTopicoDTO;
import com.mba.forohub.domain.topico.DatosTopicosDTO;

import com.mba.forohub.domain.topico.Topico;
import com.mba.forohub.domain.topico.TopicosRepository;
import com.mba.forohub.domain.usuario.Usuario;
import com.mba.forohub.domain.usuario.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;



    @PostMapping
    
    public ResponseEntity insertarTopico(@RequestBody @Valid DatosRegistroTopicoDTO datosRegistroTopicoDTO, Authentication authentication){
        var user = (Usuario)authentication.getPrincipal();
        Optional<Usuario> usuario = usuarioRepository.findById(datosRegistroTopicoDTO.idUsuario());
        if(usuario.isPresent()){
            if(user.getId() != datosRegistroTopicoDTO.idUsuario()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Usuario autor = usuario.get();
            Topico topico = new Topico(datosRegistroTopicoDTO);
            
            topico.setAutor(autor);
            topicosRepository.save(topico);
            
            URI url = UriComponentsBuilder.fromPath("topicos/{id}").buildAndExpand(datosRegistroTopicoDTO.idUsuario()).toUri();
            var body = datosRegistroTopicoDTO;
            return ResponseEntity.created(url).body(body);
        }else{
            return ResponseEntity.badRequest().build();
        }
        }


    @GetMapping("/{id}")
    public ResponseEntity verTopico(@PathVariable long id){
        Topico topico = topicosRepository.findById(id);
        
            
            DatosTopicosDTO datosTopico = new DatosTopicosDTO(
                                        topico.getId(), 
                                        topico.getTitulo(),
                                        topico.getMensaje(),
                                        topico.getFechaCreacion());
            return ResponseEntity.ok(datosTopico);

        

        
    } 
    
        

    @GetMapping
    
    public ResponseEntity<Page<DatosTopicosDTO>> listarTopicos(Pageable pageable, Authentication authentication){
        //datos del usuario autenticado
        var data = (Usuario)authentication.getPrincipal();
        
        var topic = topicosRepository.findAllByIdAutor(data.getId(), pageable).map(e -> new DatosTopicosDTO(e.getId(), e.getTitulo(), e.getMensaje(), e.getFechaCreacion()));;
      
        
        //var topicos = topicosRepository.findAll(pageable).map(e -> new DatosTopicosDTO(e.getId(), e.getTitulo(), e.getMensaje(), e.getFechaCreacion()));
        // List<DatosTopico> listaDatos = topicos.stream().map(d -> new DatosTopico(d.getId(), d.getTitulo(), d.getMensaje(), d.getFechaCreacion()))
        //                                     .collect(Collectors.toList());
        
        
        return ResponseEntity.ok(topic);
        
    }


    @PutMapping 
    @Transactional
    public ResponseEntity eliminarTopico(@RequestBody @Valid DatosActualizarTopicoDTO datosActualizarTopicoDTO, Authentication authentication){
        var topico = topicosRepository.getReferenceById(datosActualizarTopicoDTO.id());
        var user = (Usuario)authentication.getPrincipal();
        if(topico.getAutor().getId() != user.getId()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        topico.actualizarDatos(datosActualizarTopicoDTO); 
        System.out.println(topico);
        DatosTopicosDTO datosActualizados = new DatosTopicosDTO(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion());
        return ResponseEntity.ok(datosActualizados);

    }



    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id, Authentication authentication){
        var user = (Usuario)authentication.getPrincipal();
        
        var topico = topicosRepository.getReferenceById(id);

        if(topico.getAutor().getId() != user.getId()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        //topico.cambiarEstado();
        topicosRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }





}




