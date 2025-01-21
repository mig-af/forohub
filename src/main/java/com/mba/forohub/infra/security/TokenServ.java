package com.mba.forohub.infra.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mba.forohub.domain.usuario.Usuario;



@Service
public class TokenServ {

    @Value("${security-token.clave-secreta}")
    private String claveSecreta;



    public String generateToken(Usuario usuario){
        
        String token = null;
        try {
            
            Algorithm algorithm = Algorithm.HMAC256(claveSecreta);
            token = JWT.create()
                .withIssuer("forohub")
                .withSubject(usuario.getEmail())
                .withClaim("id", usuario.getId())
                .withExpiresAt(new Date(System.currentTimeMillis()+10000000))
                
                .sign(algorithm);
                
        } catch (JWTCreationException exception){
            System.out.println(exception.toString());
            //throw new RuntimeException("Error al generar el token");
        }
        return token;
        
    }


    public DecodedJWT verifyToken(String token){
            //String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
            
            DecodedJWT decodedJWT = null;
            try {
                Algorithm algorithm = Algorithm.HMAC256(claveSecreta);
                JWTVerifier verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("forohub")
                    // reusable verifier instance
                    .build();
                    
                decodedJWT = verifier.verify(token);
                
            } catch (JWTVerificationException exception){
                // Invalid signature/claims
                System.out.println(exception.toString());
                return null;
            } 
            return decodedJWT;
            
        
    }



}
