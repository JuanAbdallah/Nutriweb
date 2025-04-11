package br.com.davesmartins.nutriweb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ConsumoDiarioTest {

    @Test
    @DisplayName("Teste do método TotalCalMax")
    public void teste001(){
        ConsumoDiario cd = new ConsumoDiario(0, 10, 3000.0, null, null, null, LocalDate.of(2024, 11, 4));
        Alimento alimento1 = new Alimento(0, "alimento1", 300, "um", null);
        Alimento alimento2 = new Alimento(1, "alimento2", 150, "um", null);
        Alimento alimento3 = new Alimento(2, "alimento3", 200, "um", null);

        ConsumoDiarioAlimento cda1 = new ConsumoDiarioAlimento(0, 1, cd, alimento1);
        ConsumoDiarioAlimento cda2 = new ConsumoDiarioAlimento(0, 1, cd, alimento2);
        ConsumoDiarioAlimento cda3 = new ConsumoDiarioAlimento(0, 1, cd, alimento3);

        List<ConsumoDiarioAlimento> consumoDiarioAlimentoList = new ArrayList<>();
        consumoDiarioAlimentoList.add(cda1);
        consumoDiarioAlimentoList.add(cda2);
        consumoDiarioAlimentoList.add(cda3);

        cd.setConsumoDiarioAlimentoList(consumoDiarioAlimentoList);

        double esperado = alimento1.getCal() + alimento2.getCal() + alimento3.getCal();

        double resultado = cd.totalCalMax();

        Assertions.assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Teste do método totalCalMax retornando 0")
    public void teste002(){
        ConsumoDiario cd = new ConsumoDiario(0, 10, 3000.0, null, null, null, LocalDate.of(2024, 11, 4));

        List<ConsumoDiarioAlimento> consumoDiarioAlimentoList = new ArrayList<>();

        cd.setConsumoDiarioAlimentoList(consumoDiarioAlimentoList);

        double esperado = 0;

        double resultado = cd.totalCalMax();

        Assertions.assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Teste do método totalCalMax com alimento de caloria 0")
    public void teste003(){
        ConsumoDiario cd = new ConsumoDiario(0, 10, 3000.0, null, null, null, LocalDate.of(2024, 11, 4));
        Alimento alimento1 = new Alimento(0, "alimento1", 0, "um", null);

        ConsumoDiarioAlimento cda1 = new ConsumoDiarioAlimento(0, 1, cd, alimento1);

        List<ConsumoDiarioAlimento> consumoDiarioAlimentoList = new ArrayList<>();
        consumoDiarioAlimentoList.add(cda1);

        cd.setConsumoDiarioAlimentoList(consumoDiarioAlimentoList);

        double esperado = 0;

        double resultado = cd.totalCalMax();

        Assertions.assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Teste do metodo totalCalMax quantidade 0")
    public void teste004(){
        ConsumoDiario cd = new ConsumoDiario(0, 10, 3000.0, null, null, null, LocalDate.of(2024, 11, 4));
        Alimento alimento1 = new Alimento(0, "alimento1", 300, "um", null);

        ConsumoDiarioAlimento cda1 = new ConsumoDiarioAlimento(0, 0, cd, alimento1);

        List<ConsumoDiarioAlimento> consumoDiarioAlimentoList = new ArrayList<>();
        consumoDiarioAlimentoList.add(cda1);

        cd.setConsumoDiarioAlimentoList(consumoDiarioAlimentoList);

        double esperado = 0;

        double resultado = cd.totalCalMax();

        Assertions.assertEquals(esperado, resultado);
    }
//    @Test
//    @DisplayName("Teste do metodo totalCalMax caloria negativa")
//    public void teste005(){
//        ConsumoDiario cd = new ConsumoDiario(0, 10, 3000.0, null, null, null, LocalDate.of(2024, 11, 4));
//        Alimento alimento1 = new Alimento(0, "alimento1", -100, "um", null);
//
//        ConsumoDiarioAlimento cda1 = new ConsumoDiarioAlimento(0, 1, cd, alimento1);
//
//        List<ConsumoDiarioAlimento> consumoDiarioAlimentoList = new ArrayList<>();
//        consumoDiarioAlimentoList.add(cda1);
//
//        cd.setConsumoDiarioAlimentoList(consumoDiarioAlimentoList);
//        try{
//            double resultado = cd.totalCalMax();
//            Assertions.fail("Falhou!! Resultado não pode ser negativo: " + resultado);
//        }catch (Exception e){
//            Assertions.assertTrue(true);
//        }
//
//    }
//
//    @Test
//    @DisplayName("Teste do metodo totalCalMax quantidade negativa")
//    public void teste006(){
//        ConsumoDiario cd = new ConsumoDiario(0, 10, 3000.0, null, null, null, LocalDate.of(2024, 11, 4));
//        Alimento alimento1 = new Alimento(0, "alimento1", 100, "um", null);
//
//        ConsumoDiarioAlimento cda1 = new ConsumoDiarioAlimento(0, -1, cd, alimento1);
//
//        List<ConsumoDiarioAlimento> consumoDiarioAlimentoList = new ArrayList<>();
//        consumoDiarioAlimentoList.add(cda1);
//
//        cd.setConsumoDiarioAlimentoList(consumoDiarioAlimentoList);
//        try{
//            double resultado = cd.totalCalMax();
//            Assertions.fail("Falhou!! Resultado não pode ser negativo: " + resultado);
//        }catch (Exception e){
//            Assertions.assertTrue(true);
//        }
//
//    }
}
