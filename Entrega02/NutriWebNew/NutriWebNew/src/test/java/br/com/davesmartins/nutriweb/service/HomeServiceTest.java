package br.com.davesmartins.nutriweb.service;
import br.com.davesmartins.nutriweb.model.*;
import br.com.davesmartins.nutriweb.repo.AlimentoRepo;
import br.com.davesmartins.nutriweb.repo.ConsumoDiarioAlimentoRepo;
import br.com.davesmartins.nutriweb.repo.ConsumoDiarioRepo;
import br.com.davesmartins.nutriweb.repo.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@ActiveProfiles("test")
@SpringBootTest
public class HomeServiceTest {

    @Mock
    ConsumoDiarioRepo cdDao;
    @Mock
    AlimentoRepo aliDao;
    @Mock
    UsuarioRepo uDao;
    @Mock
    ConsumoDiarioAlimentoRepo cdaDao;

    @InjectMocks
    HomeService hServ;


    @Test
    @DisplayName("testando metodo getDatas")
    public void teste001(){

        Usuario usuario = Mockito.mock(Usuario.class);
        HorrarioRefeicao horario1 = Mockito.mock(HorrarioRefeicao.class);
        HorrarioRefeicao horario2 = Mockito.mock(HorrarioRefeicao.class);


        ConsumoDiario consumo1 = Mockito.mock(ConsumoDiario.class);
        ConsumoDiario consumo2 = Mockito.mock(ConsumoDiario.class);
        Mockito.when(consumo1.getData()).thenReturn(LocalDate.of(2023, 10, 15));
        Mockito.when(consumo1.getData()).thenReturn(LocalDate.of(2023, 11, 15));


        ArrayList<LocalDate> datasEsperadas = new ArrayList<>();
        datasEsperadas.add(LocalDate.of(2023, 10, 15));
        datasEsperadas.add(LocalDate.of(2023, 11, 15));


        Mockito.when(cdDao.retornaDatas(usuario.getIduser())).thenReturn(datasEsperadas);


        List<LocalDate> resultado = hServ.getDatas(usuario);

        Assertions.assertEquals(datasEsperadas, resultado, "As datas retornadas não correspondem ao esperado.");


    }


        @Test
        @DisplayName("Testando método getDataGrafico")
        public void teste002() {

            Usuario usuario = Mockito.mock(Usuario.class);


            Historico historico1 = Mockito.mock(Historico.class);
            Historico historico2 = Mockito.mock(Historico.class);


            Mockito.when(historico1.getDataMedida()).thenReturn(LocalDate.of(2023, 10, 15));
            Mockito.when(historico1.getPeso()).thenReturn(70.0);
            Mockito.when(historico1.imc()).thenReturn(22.5);

            Mockito.when(historico2.getDataMedida()).thenReturn(LocalDate.of(2023, 11, 15));
            Mockito.when(historico2.getPeso()).thenReturn(72.0);
            Mockito.when(historico2.imc()).thenReturn(23.0);


            List<Historico> historicoList = new ArrayList<>();
            historicoList.add(historico1);
            historicoList.add(historico2);
            Mockito.when(usuario.getHistoricoList()).thenReturn(historicoList);


            String resultado = hServ.getDataGrafico(usuario);


            String dataEsperada = "['15/10/2023',70.0,22.5],['15/11/2023',72.0,23.0],";


            Assertions.assertEquals(dataEsperada, resultado, "Os dados retornados para o gráfico não correspondem ao esperado.");
        }

        @Test
        @DisplayName("testando o metodo getListaConsumo")
        public void teste003(){
            Usuario usuario = Mockito.mock(Usuario.class);
            Mockito.when(usuario.getIduser()).thenReturn(1);
            HorrarioRefeicao horario1 = Mockito.mock(HorrarioRefeicao.class);
            HorrarioRefeicao horario2 = Mockito.mock(HorrarioRefeicao.class);


            ConsumoDiario consumo1 = Mockito.mock(ConsumoDiario.class);
            ConsumoDiario consumo2 = Mockito.mock(ConsumoDiario.class);
            Mockito.when(consumo1.getData()).thenReturn(LocalDate.of(2023, 10, 15));
            Mockito.when(consumo1.getData()).thenReturn(LocalDate.of(2023, 11, 15));

            List<ConsumoDiario> listaConsumo = new ArrayList<>();
            listaConsumo.add(consumo1);
            listaConsumo.add(consumo2);

            Mockito.when(cdDao.findByUserIduserAndData(usuario.getIduser(), LocalDate.of(2023, 10, 15))).thenReturn(listaConsumo);

            List<ConsumoDiario> resultado = hServ.getListaConsumo(usuario, LocalDate.of(2023, 10, 15));

            Assertions.assertEquals(listaConsumo, resultado, "A lista de consumo retornada não corresponde à esperada.");

        }
        @Test
        @DisplayName("testando o metodo totalConsumo")
        public void teste004(){
            Usuario usuario = Mockito.mock(Usuario.class);
            ConsumoDiario consumo1 = Mockito.mock(ConsumoDiario.class);
            ConsumoDiario consumo2 = Mockito.mock(ConsumoDiario.class);

            Mockito.when(consumo1.getCalMax()).thenReturn(250.0);
            Mockito.when(consumo2.getCalMax()).thenReturn(350.0);

            double resultado = consumo1.getCalMax() + consumo2.getCalMax();

            double esperado = 250 + 350;

            Assertions.assertEquals(esperado,resultado, "o total de consumo não é o esperado");

        }

        @Test
        @DisplayName("testando o metodo totalCalMax")
        public void teste005(){
            Usuario usuario = Mockito.mock(Usuario.class);
            ConsumoDiario consumo1 = Mockito.mock(ConsumoDiario.class);
            ConsumoDiario consumo2 = Mockito.mock(ConsumoDiario.class);

            Mockito.when(consumo1.getCalMax()).thenReturn(250.0);
            Mockito.when(consumo2.getCalMax()).thenReturn(350.0);

            double resultado = consumo1.getCalMax() + consumo2.getCalMax();

            double esperado = 250 + 350;

            Assertions.assertEquals(esperado,resultado, "o total de consumo não é o esperado");
        }

        @Test
        @DisplayName("testando o metodo listaAlimentos")
        public void teste006(){
            Alimento alimento1 = Mockito.mock(Alimento.class);
            Alimento alimento2 = Mockito.mock(Alimento.class);

            ArrayList<Alimento> alimentos = new ArrayList<>();

            alimentos.add(alimento1);
            alimentos.add(alimento2);

            Mockito.when(aliDao.findAll()).thenReturn(alimentos);

            List<Alimento> resultado = hServ.listaAlimentos();

            Assertions.assertEquals(alimentos.size(),resultado.size(),"Não foi possivel encontrar a lista de alimentos");


        }

        @Test
        @DisplayName("testando o metodo saveAlimentos")
        public void teste007(){
            Usuario usuario = Mockito.mock(Usuario.class);
            ConsumoDiario consumoDiario = Mockito.mock(ConsumoDiario.class);
            Alimento alimento = Mockito.mock(Alimento.class);
            ConsumoDiarioAlimento cda = Mockito.mock(ConsumoDiarioAlimento.class);

            Mockito.when(uDao.getReferenceById(usuario.getIduser())).thenReturn(usuario);

            Mockito.when(cdDao.findByUserIduserAndHorrarioRefeicaoIdhorrarioRefeicaoAndData(
                    usuario.getIduser(), 1, LocalDate.of(2023, 10, 15)))
                    .thenReturn(consumoDiario);

            Mockito.when(aliDao.getById(1)).thenReturn(alimento);

            hServ.saveAlimentos(usuario, LocalDate.of(2023, 10, 15), 1, 1, 100.0);

            Mockito.verify(cdaDao).save(Mockito.any(ConsumoDiarioAlimento.class));
        }

    @Test
    @DisplayName("Testando o método getDataGrafico com histórico vazio")
    public void teste008() {
        Usuario usuario = Mockito.mock(Usuario.class);

        Mockito.when(usuario.getHistoricoList()).thenReturn(Collections.emptyList());

        String resultado = hServ.getDataGrafico(usuario);

        Assertions.assertEquals("", resultado, "O gráfico não deveria ter dados.");
    }
    @Test
    @DisplayName("Testando o método totalConsumo com lista vazia")
    public void teste009() {
        Usuario usuario = Mockito.mock(Usuario.class);

        Mockito.when(usuario.getConsumoDiarioList()).thenReturn(Collections.emptyList());

        double resultado = hServ.totalConsumo(usuario);

        Assertions.assertEquals(0.0, resultado, "O total de consumo deveria ser 0 quando não houver dados.");
    }

    @Test
    @DisplayName("Testando o método totalCalMax com lista vazia")
    public void teste010() {
        Usuario usuario = Mockito.mock(Usuario.class);

        Mockito.when(usuario.getConsumoDiarioList()).thenReturn(Collections.emptyList());

        double resultado = hServ.totalCalMax(usuario);

        Assertions.assertEquals(0.0, resultado, "O total de calorias máximas deveria ser 0 quando não houver dados.");
    }

    @Test
    @DisplayName("Testando o método getListaConsumo com lista vazia")
    public void teste011() {
        Usuario usuario = Mockito.mock(Usuario.class);


        Mockito.when(usuario.getIduser()).thenReturn(1);
        Mockito.when(cdDao.findByUserIduserAndData(usuario.getIduser(), LocalDate.of(2023, 10, 15)))
                .thenReturn(Collections.emptyList());

        List<ConsumoDiario> resultado = hServ.getListaConsumo(usuario, LocalDate.of(2023, 10, 15));

        Assertions.assertTrue(resultado.isEmpty(), "A lista de consumo deveria estar vazia.");
    }



}
