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
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daves
 */
@Entity
@Table(name = "consumodiario_alimento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumoDiarioAlimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private double qtde;

//    @JoinColumns({
//        @JoinColumn(name = "iduser", referencedColumnName = "iduser", nullable = false, insertable = false, updatable = false),
//        @JoinColumn(name = "idTipoRefeicao", referencedColumnName = "idTipoRefeicao", nullable = false, insertable = false, updatable = false),
//        @JoinColumn(name = "data", referencedColumnName = "data", nullable = false, insertable = false, updatable = false)})

//    @JoinColumn(name = "idconsumoDiario", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ConsumoDiario consumoDiario;

//    @JoinColumn(name = "idalimento", referencedColumnName = "idalimento", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alimento alimento;

    
}
