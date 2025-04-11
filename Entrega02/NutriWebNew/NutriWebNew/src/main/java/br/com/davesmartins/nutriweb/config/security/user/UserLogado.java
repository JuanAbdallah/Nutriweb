/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davesmartins.nutriweb.config.security.user;

import java.util.Set;

import br.com.davesmartins.nutriweb.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author daves
 */
@Data
public class UserLogado extends User{

//    private Integer id;
//    private String nome, email;

    private Usuario user;
    
    public UserLogado(Usuario user, String login, String senha, Set<GrantedAuthority> grantedAuthorities ) {
        super(login, senha, grantedAuthorities);
        this.user = user;
    }
    
}
