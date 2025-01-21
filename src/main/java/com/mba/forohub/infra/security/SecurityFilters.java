package com.mba.forohub.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mba.forohub.domain.usuario.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class SecurityFilters extends OncePerRequestFilter{

    

    @Autowired
    private TokenServ tokenServ;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        
        System.out.println("El filtro se esta ejecutando");
        System.out.println(request.getRequestURI());
        var tokenAuthorization = request.getHeader("Authorization");

        

        if(tokenAuthorization != null){
            System.out.println("El token si existe");
            tokenAuthorization = tokenAuthorization.replace("Bearer ", "");
            
            var tokenVerificado = tokenServ.verifyToken(tokenAuthorization);
            if(tokenVerificado != null){
                var email = tokenVerificado.getSubject();
                //System.out.println(tokenVerificado.getClaims());
                //System.out.println(tokenAuthorization);

                var user = repository.findByEmail(email);
                //System.out.println(user.getAuthorities());

                var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }


        }else{
            System.out.println("El token no existe");
        }
        
        filterChain.doFilter(request, response);
    }



}
