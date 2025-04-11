/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davesmartins.nutriweb.util;


import br.com.davesmartins.nutriweb.config.security.user.UserLogado;
import br.com.davesmartins.nutriweb.model.Historico;
import br.com.davesmartins.nutriweb.model.Usuario;
import br.com.davesmartins.nutriweb.repo.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

/**
 *
 * @author daves
 */
@Service
public class LogadoUtil {

    @Autowired
    UsuarioRepo uRepo;
	 
    public Integer getIdUserLogado(Authentication authentication){
     
        return ((Usuario)((UserLogado)authentication.getPrincipal()).getUser()).getIduser();
    }
     
    public String getNomeUserLogado(Authentication authentication){
     
        return ((Usuario)((UserLogado)authentication.getPrincipal()).getUser()).getNome();
    }
     
    public String getEmailUserLogado(Authentication authentication){
     
        return ((Usuario)((UserLogado)authentication.getPrincipal()).getUser()).getEmail();
    }
    public Usuario getUserLogado(Authentication authentication){
        return uRepo.findById(((UserLogado)authentication.getPrincipal()).getUser().getIduser()).get();
    }
     
    public boolean eHAdministrador(Authentication authentication){     
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
