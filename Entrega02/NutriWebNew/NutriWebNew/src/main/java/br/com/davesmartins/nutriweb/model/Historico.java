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
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daves
 */
@Entity
@Table( schema = "",name = "historico")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQueries({
    @NamedQuery(name = "Historico.findAll", query = "SELECT h FROM Historico h"),
    //@NamedQuery(name = "Historico.findByIdhistorico", query = "SELECT h FROM Historico h WHERE h.historicoPK.idhistorico = :idhistorico"),
    //@NamedQuery(name = "Historico.findByIduser", query = "SELECT h FROM Historico h WHERE h.historicoPK.iduser = :iduser"),
    @NamedQuery(name = "Historico.findByPeso", query = "SELECT h FROM Historico h WHERE h.peso = :peso"),
    @NamedQuery(name = "Historico.findByAltura", query = "SELECT h FROM Historico h WHERE h.altura = :altura"),
    @NamedQuery(name = "Historico.findByDataMedida", query = "SELECT h FROM Historico h WHERE h.dataMedida = :dataMedida")})
public class Historico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Column(nullable = false)
    private double peso;
    @Basic(optional = false)
    @Column(nullable = false)
    private double altura;
    @Basic(optional = false)
    @Column(nullable = false)
//    @Temporal(TemporalType.DATE)
    private LocalDate dataMedida;

    @JoinColumn(name = "iduser", referencedColumnName = "iduser", nullable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario user;

    public double imc(){
        return peso/(altura*altura);
    }

//    public String imcFormatado() {
//        return new DecimalFormat("#0.00").format(imc());
//    }
}
