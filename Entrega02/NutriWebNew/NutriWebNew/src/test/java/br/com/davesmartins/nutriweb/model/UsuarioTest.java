package br.com.davesmartins.nutriweb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UsuarioTest {

    @Test
    @DisplayName("Teste de IMC com um único histórico válido")
    public void teste001() {
        Historico historico = new Historico(1, 94.00, 1.80, LocalDate.of(2024, 11, 04), null);
        List<Historico> historicoList = new ArrayList<>();
        historicoList.add(historico);

        Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha", "objetivo", historicoList, null);

        double esperado = 94.0 / (1.80 * 1.80);
        Assertions.assertEquals(esperado, usuario.imc(), "Falha no cálculo do IMC com histórico válido");
    }

    @Test
    @DisplayName("Teste de IMC com lista de histórico vazia")
    public void teste002() {
        Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha", "objetivo", new ArrayList<>(), null);
        Assertions.assertEquals(0, usuario.imc(), "Falha ao retornar IMC zero para lista de histórico vazia");
    }

    @Test
    @DisplayName("Teste de IMC com histórico nulo")
    public void teste003() {
        Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha", "objetivo", null, null);
        Assertions.assertEquals(0, usuario.imc(), "Falha ao retornar IMC zero para histórico nulo");
    }
    //perguntar para o Daves se é para o método retornar 0 mesmo ou se o correto era retornar um erro!

    @Test
    @DisplayName("Teste de IMC com múltiplos históricos (usa o último)")
    public void teste004() {
        Historico historico1 = new Historico(1, 94.0, 1.80, LocalDate.of(2024, 10, 01), null);
        Historico historico2 = new Historico(2, 90.0, 1.80, LocalDate.of(2024, 11, 04), null);

        List<Historico> historicoList = new ArrayList<>();
        historicoList.add(historico1);
        historicoList.add(historico2);

        Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha", "objetivo", historicoList, null);

        double esperado = 90 / (1.80 * 1.80);
        Assertions.assertEquals(esperado, usuario.imc(), "Falha no cálculo do IMC usando o último histórico");
    }

//    @Test
//    @DisplayName("Teste de IMC com valor de peso igual a zero no último histórico")
//    public void teste005() {
//        Historico historico1 = new Historico(1, 94.0, 1.80, LocalDate.of(2024, 10, 01), null);
//        Historico historico2 = new Historico(2, 0.0, 1.80, LocalDate.of(2024, 11, 04), null);
//
//        List<Historico> historicoList = new ArrayList<>();
//        historicoList.add(historico1);
//        historicoList.add(historico2);
//
//        Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha", "objetivo", historicoList, null);
//
//        try {
//            usuario.imc();
//            Assertions.fail("Falhou! O IMC não deve ser calculado com peso igual a zero. Resultado: " + usuario.imc());
//        } catch (Exception e) {
//            Assertions.assertTrue(true);
//        }
//    }
//
//    @Test
//    @DisplayName("Teste de IMC com valor de altura igual a zero no último histórico")
//    public void teste006() {
//        Historico historico1 = new Historico(1, 94.0, 1.80, LocalDate.of(2024, 10, 01), null);
//        Historico historico2 = new Historico(2, 90.0, 0.0, LocalDate.of(2024, 11, 04), null);
//
//        List<Historico> historicoList = new ArrayList<>();
//        historicoList.add(historico1);
//        historicoList.add(historico2);
//
//        Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha", "objetivo", historicoList, null);
//
//        try {
//            usuario.imc();
//            Assertions.fail("Falhou! O IMC não deve ser calculado com altura igual a zero. Resultado: " + usuario.imc());
//        } catch (Exception e) {
//            Assertions.assertTrue(true);
//        }
//    }
//
//    @Test
//    @DisplayName("Teste de IMC com valores negativos no último histórico")
//    public void teste007() {
//        Historico historico1 = new Historico(1, 94.0, 1.80, LocalDate.of(2024, 10, 01), null);
//        Historico historico2 = new Historico(2, -90.0, -1.80, LocalDate.of(2024, 11, 04), null);
//
//        List<Historico> historicoList = new ArrayList<>();
//        historicoList.add(historico1);
//        historicoList.add(historico2);
//
//        Usuario usuario = new Usuario(0, "nome", "email@email.com", "senha", "objetivo", historicoList, null);
//
//        try {
//            usuario.imc();
//            Assertions.fail("Falhou! O IMC não deve ser calculado com valores negativos para peso e altura.Resultado: " + usuario.imc());
//        } catch (Exception e) {
//            Assertions.assertTrue(true);
//        }
//    }
}
