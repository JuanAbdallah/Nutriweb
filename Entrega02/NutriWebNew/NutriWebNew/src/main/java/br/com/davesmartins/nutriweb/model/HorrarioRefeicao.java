/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davesmartins.nutriweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
@Table( schema = "",name="horrariorefeicao")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "HorrarioRefeicao.findAll", query = "SELECT h FROM HorrarioRefeicao h"),
    @NamedQuery(name = "HorrarioRefeicao.findByIdhorrarioRefeicao", query = "SELECT h FROM HorrarioRefeicao h WHERE h.idhorrarioRefeicao = :idhorrarioRefeicao"),
    @NamedQuery(name = "HorrarioRefeicao.findByDescricao", query = "SELECT h FROM HorrarioRefeicao h WHERE h.descricao = :descricao")})
public class HorrarioRefeicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idhorrarioRefeicao;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horrarioRefeicao")
    private List<ConsumoDiario> consumoDiarioList;

}
