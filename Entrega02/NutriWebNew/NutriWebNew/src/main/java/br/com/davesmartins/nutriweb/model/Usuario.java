/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davesmartins.nutriweb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daves
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "User.findByIduser", query = "SELECT u FROM Usuario u WHERE u.iduser = :iduser"),
    @NamedQuery(name = "User.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "User.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha"),
    @NamedQuery(name = "User.findByLoginSenha", query = "SELECT u FROM Usuario u WHERE u.email = :email and u.senha = :senha"),
    @NamedQuery(name = "User.findByObjetivo", query = "SELECT u FROM Usuario u WHERE u.objetivo = :objetivo")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer iduser;

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = false, length = 95)
    private String email;

    @Column(nullable = false, length = 150)
    private String senha;

    @Column(nullable = false, length = 100)
    private String objetivo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Historico> historicoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ConsumoDiario> consumoDiarioList;


    public double imc() {
        if ((historicoList == null) || (historicoList.size() == 0)){
            return 0;
        } else
        return historicoList.get(historicoList.size()-1).imc();
    }
}
