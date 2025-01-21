package com.mba.forohub.infra.errors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorErrores {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> tratarError404(){
        return ResponseEntity.notFound().build();
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException err){
        var info = err.getBody();
        var error = err.getFieldError();
        MostrarMensajeError mensajeError = new MostrarMensajeError(info.getStatus(),
                                                 info.getTitle(),
                                                 error.getField(),
                                                 error.getDefaultMessage());
        System.out.println(info);
        System.out.println("----------------------------------------------------------------------");
        //var siu = err.str
        return ResponseEntity.badRequest().body(mensajeError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity erroDatosDuplicados(DataIntegrityViolationException dataIntegrityViolationException){
        
        var de = dataIntegrityViolationException.getMostSpecificCause();
        System.out.println("----------------------------------------------------------");
        System.out.println(de.getMessage());
        System.out.println("-----------------------------------------------------------");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(de.getMessage());
    }



    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpRequestMethodNotSupportedException> tratarErro405(){

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity tratarErrorNoResource(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }






    public record MostrarMensajeError(
        int status,
        String error,
        String field,
        String defaultMessage


    ) {
    }




}
