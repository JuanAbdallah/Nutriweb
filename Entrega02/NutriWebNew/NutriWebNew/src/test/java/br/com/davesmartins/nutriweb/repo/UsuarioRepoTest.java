package br.com.davesmartins.nutriweb.repo;

import br.com.davesmartins.nutriweb.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class UsuarioRepoTest {
    Usuario usuario = new Usuario(
            1,
            "pedrin",
            "email@email",
            "senha",
            "emagrecer",
            new ArrayList<>(),
            new ArrayList<>()
    );
    @Mock
    private UsuarioRepo uDao;


    @Test
    @DisplayName("Testando o findByNome")
    public void teste001(){
        Usuario esperado = usuario;

        Mockito.when(uDao.findByNome("pedrin")).thenReturn(Optional.of(usuario));

        Usuario obtido = uDao.findByNome("pedrin").get();

        Assertions.assertEquals(esperado,obtido);
    }

    @Test
    @DisplayName("Testando o findByEmail")
    public void teste002(){
        Usuario esperado = usuario;

        Mockito.when(uDao.findByEmail("email@email")).thenReturn(Optional.of(usuario));

        Usuario obtido = uDao.findByEmail("email@email").get();

        Assertions.assertEquals(esperado,obtido);
    }
}
