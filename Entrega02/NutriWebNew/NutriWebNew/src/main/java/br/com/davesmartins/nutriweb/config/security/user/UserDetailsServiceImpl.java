/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davesmartins.nutriweb.config.security.user;


import java.util.HashSet;

import java.util.Optional;
import java.util.Set;

import br.com.davesmartins.nutriweb.model.Usuario;
import br.com.davesmartins.nutriweb.repo.UsuarioRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author daves
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Value("${project.config.passwordAdmin}")
    private String passwordValue;
    @Value("${project.config.userAdmin}")
    private String userValue;
//    @Autowired
//    private PasswordEncoder password;
    @Autowired
    private UsuarioRepo userRepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Optional<Usuario> userC = userRepo.findByEmail(username);

        if (userC.isPresent()) {

            if (username.equals(userValue)) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                //return new User(userValue, password.encode(passwordValue), grantedAuthorities);
//            } else if (!userC.get().isAtivo()) {
//                throw new UsernameNotFoundException(username);
            } else {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
            }
            return new UserLogado(userC.get(),username, userC.get().getSenha(), grantedAuthorities );
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

}
