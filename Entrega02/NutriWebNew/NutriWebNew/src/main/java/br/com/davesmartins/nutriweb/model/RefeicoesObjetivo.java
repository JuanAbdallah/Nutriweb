/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davesmartins.nutriweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Daves
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefeicoesObjetivo {
    private String objetivo;
    private String tipoRefeicao;
    private int horario;
    private double calMax;

    
    
}
