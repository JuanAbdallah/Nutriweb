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
@Table( schema = "",name = "alimento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Alimento.findAll", query = "SELECT a FROM Alimento a order by a.descricao"),
    @NamedQuery(name = "Alimento.findByIdalimento", query = "SELECT a FROM Alimento a WHERE a.idalimento = :idalimento"),
    @NamedQuery(name = "Alimento.findByDescricao", query = "SELECT a FROM Alimento a WHERE a.descricao = :descricao"),
    @NamedQuery(name = "Alimento.findByCal", query = "SELECT a FROM Alimento a WHERE a.cal = :cal"),
    @NamedQuery(name = "Alimento.findByUnidade", query = "SELECT a FROM Alimento a WHERE a.unidade = :unidade")})
public class Alimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idalimento;
    @Basic(optional = false)
    @Column(nullable = false, length = 95)
    private String descricao;
    @Basic(optional = false)
    @Column(nullable = false)
    private int cal;
    @Basic(optional = false)
    @Column(nullable = false, length = 8)
    private String unidade;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alimento")
    private List<ConsumoDiarioAlimento> consumoDiarioAlimentoList;

}
