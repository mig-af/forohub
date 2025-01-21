package com.mba.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mba.forohub.domain.autor.AutorRepository;
import com.mba.forohub.domain.autor.DatosAutorRespuestaDTO;
import com.mba.forohub.domain.usuario.Usuario;
import com.mba.forohub.domain.usuario.UsuarioRepository;

@RestController
@RequestMapping("/usuario")

public class AutorController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AutorRepository autorRepository;


    @GetMapping
    public ResponseEntity infoUsuario(Authentication authentication){
        var usuarioAutenticado = (Usuario)authentication.getPrincipal();
        System.out.println(usuarioAutenticado.getId());
        var usuario = autorRepository.getReferenceById(usuarioAutenticado.getId());
        System.out.println(usuario);
        var user = new DatosAutorRespuestaDTO(usuario.getNombre(), usuario.getApellido(), usuario.getEmail());
        return ResponseEntity.ok(user);
    }
}
