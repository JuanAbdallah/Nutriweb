package br.com.davesmartins.nutriweb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class HistoricoTest {
    @Test
    @DisplayName("Teste de imc")
    public void teste001(){
        Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha", "objetivo", null, null);
        Historico historico = new Historico(0, 94.00, 1.80, LocalDate.of(2024, 11, 04), usuario);

        double esperado = 94/(1.80*1.80);

        Assertions.assertEquals(historico.imc(), esperado);
    }

//    @Test
//    @DisplayName("Teste de imc com peso 0")
//    public void teste002(){
//        Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha", "objetivo", null, null);
//        Historico historico = new Historico(0, 0, 1.80, LocalDate.of(2024, 11, 04), usuario);
//
//        try{
//            double resultado = historico.imc();
//            Assertions.fail("Falhou! O valor de peso nao pode ser 0! Resultado: " + resultado);
//        }catch (Exception e){
//            Assertions.assertTrue(true);
//        }
//    }
//
//    @Test
//    @DisplayName("Teste de imc com altura 0")
//    public void teste003(){
//        Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha", "objetivo", null, null);
//        Historico historico = new Historico(0, 94.00, 0, LocalDate.of(2024, 11, 04), usuario);
//
//        try{
//            double resultado = historico.imc();
//            Assertions.fail("Falhou! O valor de peso nao pode ser 0! Resultado: " + resultado);
//        }catch (Exception e){
//            Assertions.assertTrue(true);
//        }
//    }
//
//    @Test
//    @DisplayName("Teste de imc com peso negativo")
//    public void teste004(){
//        Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha", "objetivo", null, null);
//        Historico historico = new Historico(0, -90.00, 1.80, LocalDate.of(2024, 11, 04), usuario);
//
//        try{
//            double resultado = historico.imc();
//            Assertions.fail("Falhou! O valor de peso nao pode ser negativo! Resultado: " + resultado);
//        }catch (Exception e){
//            Assertions.assertTrue(true);
//        }
//    }
//
//    @Test
//    @DisplayName("Teste de imc com altura negativo")
//    public void teste005(){
//        Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha", "objetivo", null, null);
//        Historico historico = new Historico(0, 94.00, -1.80, LocalDate.of(2024, 11, 04), usuario);
//
//        try{
//            double resultado = historico.imc();
//            Assertions.fail("Falhou! O valor de peso nao pode ser negativo! Resultado: " + resultado);
//        }catch (Exception e){
//            Assertions.assertTrue(true);
//        }
//    }
//
    @Test
    @DisplayName("Teste de IMC com valores extremos para peso e altura")
    public void teste006(){
        Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha", "objetivo", null, null);
        Historico historico = new Historico(0, 500.00, 3.00, LocalDate.of(2024, 11, 04), usuario);

        double esperado = 500.00 / (3.00 * 3.00);

        Assertions.assertEquals(esperado, historico.imc(), "Falha no cálculo do IMC com valores extremos");
    }
//
//    @Test
//    @DisplayName("Teste de criação de Historico com data nula")
//    public void teste007(){
//        Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha", "objetivo", null, null);
//        try {
//            Historico historico = new Historico(0, 94.00, 1.80, null, usuario);
//            Assertions.fail("Falhou! A data não pode ser nula.");
//        } catch (Exception e) {
//            Assertions.assertTrue(true);
//        }
//    }
//
//    @Test
//    @DisplayName("Teste de criação de Historico com usuário nulo")
//    public void teste008(){
//        try {
//            Historico historico = new Historico(0, 94.00, 1.80, LocalDate.of(2024, 11, 04), null);
//            Assertions.fail("Falhou! O usuário não pode ser nulo.");
//        } catch (Exception e) {
//            Assertions.assertTrue(true);
//        }
//    }
}
