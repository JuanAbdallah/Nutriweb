package br.com.davesmartins.nutriweb.repo;

import br.com.davesmartins.nutriweb.model.ConsumoDiario;
import br.com.davesmartins.nutriweb.model.ConsumoDiarioAlimento;
import br.com.davesmartins.nutriweb.model.HorrarioRefeicao;
import br.com.davesmartins.nutriweb.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ConsumoDiarioRepoTest {
    Usuario usuario = new Usuario(1, null, null, null, null, null, null);
    HorrarioRefeicao horrarioRefeicao = new HorrarioRefeicao(1, null, null);

    ConsumoDiario consumoDiario = new ConsumoDiario(1, null, 500.00,new ArrayList<ConsumoDiarioAlimento>(), usuario, horrarioRefeicao, LocalDate.now());

    @Mock
    private ConsumoDiarioRepo cDao;
    @Test
    @DisplayName("testando o metodo findByUserIduserAndData")
    public void teste001(){
        List<ConsumoDiario> esperado = new ArrayList<>();

        Mockito.when(cDao.findByUserIduserAndData(usuario.getIduser(),consumoDiario.getData())).thenReturn(new ArrayList<ConsumoDiario>());

        List<ConsumoDiario> resultado = cDao.findByUserIduserAndData(usuario.getIduser(),consumoDiario.getData());

        Assertions.assertEquals(esperado,resultado);
    }

    @Test
    @DisplayName("Testando o método findByUserIduserAndHorrarioRefeicaoIdhorrarioRefeicaoAndData")
    public void teste002() {

      ConsumoDiario esperado = consumoDiario;



        Mockito.when(cDao.findByUserIduserAndHorrarioRefeicaoIdhorrarioRefeicaoAndData(
                usuario.getIduser(),
                horrarioRefeicao.getIdhorrarioRefeicao(),
                consumoDiario.getData()
        )).thenReturn(consumoDiario);


        ConsumoDiario resultado = cDao.findByUserIduserAndHorrarioRefeicaoIdhorrarioRefeicaoAndData(
                usuario.getIduser(),
                horrarioRefeicao.getIdhorrarioRefeicao(),
                consumoDiario.getData()
        );


        Assertions.assertEquals(esperado, resultado);

    }
}
