package com.mba.forohub.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mba.forohub.domain.usuario.UsuarioRepository;





@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override 
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
        return repository.findByEmail(username);
    }

    
}
