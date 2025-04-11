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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daves
 */
@Entity
@Table( schema = "",name = "consumodiario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumoDiario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    private Integer horario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double calMax;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consumoDiario")
    private List<ConsumoDiarioAlimento> consumoDiarioAlimentoList;

//    @JoinColumn(name = "iduser", referencedColumnName = "iduser", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario user;

//    @JoinColumn(name = "idhorrarioRefeicao", referencedColumnName = "idhorrarioRefeicao", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private HorrarioRefeicao horrarioRefeicao;

    @NotNull
    private LocalDate data;

    public double totalCalMax(){
        double mx = 0;
        for (ConsumoDiarioAlimento cda :
                consumoDiarioAlimentoList) {
            mx += (cda.getQtde() * cda.getAlimento().getCal());
        }
        return mx;
    }

}
