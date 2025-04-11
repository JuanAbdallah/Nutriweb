package br.com.davesmartins.nutriweb.model.DTO;

import br.com.davesmartins.nutriweb.model.Usuario;
import br.com.davesmartins.nutriweb.model.dto.CadastroUsuarioDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class CadastroUsuarioDTOTest {
    private CadastroUsuarioDTO cadastroUsuarioDTO;

    @BeforeEach
    public void setUp(){
        cadastroUsuarioDTO = new CadastroUsuarioDTO(
                "Tião",
                "senha",
                "senha",
                "email@email.com",
                94.00,
                1.80
        );
    }

    @Test
    @DisplayName("Teste conversão para usuário")
    public void teste001() {
        Usuario usuarioConvertido = cadastroUsuarioDTO.ConvertUsuario();

        Assertions.assertEquals("Tião", usuarioConvertido.getNome());
        Assertions.assertEquals("senha", usuarioConvertido.getSenha());
        Assertions.assertEquals("email@email.com", usuarioConvertido.getEmail());
        Assertions.assertEquals("Emagrecer", usuarioConvertido.getObjetivo());
        Assertions.assertNotNull(usuarioConvertido.getHistoricoList());
        Assertions.assertTrue(usuarioConvertido.getHistoricoList().isEmpty());
        Assertions.assertNotNull(usuarioConvertido.getConsumoDiarioList());
        Assertions.assertTrue(usuarioConvertido.getConsumoDiarioList().isEmpty());
    }

//    @Test
//    @DisplayName("Teste altura e peso devem ser positivos")
//    public void teste002(){
//        cadastroUsuarioDTO.setAltura(-1.80);
//        cadastroUsuarioDTO.setPeso(-90);
//
//        Usuario usuario = cadastroUsuarioDTO.ConvertUsuario();
//
////        Assertions.assertFalse(usuario.get);
//    }
}
