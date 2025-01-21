package com.mba.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mba.forohub.domain.autor.Autor;
import com.mba.forohub.domain.autor.AutorRepository;
import com.mba.forohub.domain.autor.DatosAutorRespuestaDTO;
import com.mba.forohub.domain.usuario.DatosUsuarioLoginDTO;
import com.mba.forohub.domain.usuario.DatosUsuarioRegistro;
import com.mba.forohub.domain.usuario.Usuario;
import com.mba.forohub.domain.usuario.UsuarioRepository;
import com.mba.forohub.infra.security.TokenServ;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController

public class AuthController {

    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AutorRepository autorRepository;


    @Autowired
    private TokenServ tokenServ;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DatosUsuarioLoginDTO datosLoginDTO){
        //System.out.println(datosLoginDTO);
        Authentication auth = new UsernamePasswordAuthenticationToken(datosLoginDTO.email(), datosLoginDTO.password());
        var autenticado = authenticationManager.authenticate(auth);
        //System.out.println(autenticado.getPrincipal());

        
        var token =tokenServ.generateToken((Usuario)autenticado.getPrincipal());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/registro")
    public ResponseEntity getMethodName(@RequestBody @Valid DatosUsuarioRegistro datosUsuarioRegistro) {
        var autor = new Autor(datosUsuarioRegistro);
        DatosAutorRespuestaDTO autorRegistrado = new DatosAutorRespuestaDTO(autor.getNombre(), autor.getApellido(), autor.getEmail());
        autorRepository.save(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorRegistrado);
    }
        

}
