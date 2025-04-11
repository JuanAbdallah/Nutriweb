package br.com.davesmartins.nutriweb.service;

import br.com.davesmartins.nutriweb.model.ConsumoDiario;
import br.com.davesmartins.nutriweb.model.Historico;
import br.com.davesmartins.nutriweb.model.HorrarioRefeicao;
import br.com.davesmartins.nutriweb.model.Usuario;
import br.com.davesmartins.nutriweb.model.dto.CadastroUsuarioDTO;
import br.com.davesmartins.nutriweb.model.dto.UsuarioPesoDTO;
import br.com.davesmartins.nutriweb.repo.ConsumoDiarioRepo;
import br.com.davesmartins.nutriweb.repo.HistoricoRepo;
import br.com.davesmartins.nutriweb.repo.HorrarioRefeicaoRepo;
import br.com.davesmartins.nutriweb.repo.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    UsuarioRepo uDao;

    @Mock
    HistoricoRepo hisDao;

    @Mock
    ConsumoDiarioRepo crDao;

    @Mock
    HorrarioRefeicaoRepo horaDao;

    @InjectMocks
    UsuarioService uServ;

    @Test
    @DisplayName("Testar salvamento de usuário com objetivo de emagrecer")
    public void testeSalvarUsuarioObjetivoEmagrecer() {
        CadastroUsuarioDTO cadUserDTO = new CadastroUsuarioDTO(
                "Nome",
                "senha",
                "senha",
                "email@email.com",
                94.00,
                1.80
        );

        Usuario usuario = cadUserDTO.ConvertUsuario();

        Mockito.when(uDao.save(Mockito.any(Usuario.class))).thenReturn(usuario);

        List<Historico> historicos = List.of(
                new Historico(0, 94.00, 1.80, LocalDate.of(2024, 10, 6), usuario),
                new Historico(0, 90.00, 1.80, LocalDate.of(2024, 11, 6), usuario)
        );
        Mockito.when(hisDao.saveAll(Mockito.anyList())).thenReturn(historicos);

        HorrarioRefeicao horrarioRefeicao = new HorrarioRefeicao(0, "Café da Manhã", null);
        Mockito.when(horaDao.findAll()).thenReturn(List.of(horrarioRefeicao));

        ConsumoDiario consumoDiario = new ConsumoDiario(
                0, 8, 300.0, new ArrayList<>(), usuario, horrarioRefeicao, LocalDate.of(2024, 11, 6)
        );
        Mockito.when(crDao.save(Mockito.any(ConsumoDiario.class))).thenReturn(consumoDiario);
//        System.out.println("Usuario antes: " + usuario);
        uServ.save(cadUserDTO);
//        System.out.println("Usuario depois: " + usuario);


        Assertions.assertFalse(usuario.getConsumoDiarioList().isEmpty(), "Consumo diário não foi adicionado ao usuário.");
        Assertions.assertEquals(1, usuario.getConsumoDiarioList().size(), "Quantidade de consumos diários adicionados está incorreta.");
    }

    @Test
    @DisplayName("testando o metodo changeObjetivo")
    public void teste002(){
        Usuario u = Mockito.mock(Usuario.class);
        Mockito.when(u.getObjetivo()).thenReturn("Ganhar Massa");

        String novoObjetivo = "Perder Peso";

        Mockito.when(uDao.save(u)).thenReturn(u);
        uServ.changeObjetivo(u, novoObjetivo);

        verify(u).setObjetivo(novoObjetivo);
        verify(uDao).save(u);


    }

//    @Test
//    @DisplayName("testando o metodo gravaHistorico")
//    public void teste003(){
//
//
//        Usuario userMock = Mockito.mock(Usuario.class);
//        UsuarioPesoDTO usuarioPesoMock = Mockito.mock(UsuarioPesoDTO.class);
//
//
//        Mockito.when(usuarioPesoMock.getPeso()).thenReturn(70.0);
//        Mockito.when(usuarioPesoMock.getAltura()).thenReturn(1.75);
//
//
//        ArrayList<Historico> historicoList = new ArrayList<>();
//        Mockito.when(userMock.getHistoricoList()).thenReturn(historicoList);
//
//
//        uServ.gravaHistorico(userMock, usuarioPesoMock);
//
//
//        Mockito.verify(hisDao).save(Mockito.any(Historico.class));
//
//
//
//    }

}
