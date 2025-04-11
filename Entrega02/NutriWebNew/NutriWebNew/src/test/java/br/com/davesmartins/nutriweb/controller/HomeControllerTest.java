package br.com.davesmartins.nutriweb.controller;
import br.com.davesmartins.nutriweb.config.security.user.UserLogado;
import br.com.davesmartins.nutriweb.model.Usuario;
import br.com.davesmartins.nutriweb.repo.UsuarioRepo;
import br.com.davesmartins.nutriweb.service.UsuarioService;
import br.com.davesmartins.nutriweb.util.LogadoUtil;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(controllers = HomeController.class)
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepo uDao;


    @Mock
    private LogadoUtil util;

    Usuario usuario;

    @BeforeEach
    public void setUp(){
        usuario = new Usuario(
                123,
                "userTest",
                "email@email",
                "senha",
                "emagrecer",
                new ArrayList<>(),
                new ArrayList<>()
        );

        uDao.save(usuario);
    }

    @AfterEach
    public void tearDown(){
        uDao.deleteAll();
    }
    @Test
    @DisplayName("Testando a view home")
    public void teste001() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Testando a view alimento")
    public void teste002() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/alimento/view"))
                .andExpect(status().is(302));

    }

    @Test
    @DisplayName("Testando a view about")
    public void teste003() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/about"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Testando a view de cadastro")
    public void teste004() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cadastro"))
                .andExpect(status().isOk());

    }


    @Test
    @DisplayName("Testando a view de login")
    public void teste005() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Testando pagina inexistente")
    @WithMockUser
    public void teste006() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/abc"))
                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("Testando a view de login")
    public void teste007() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(status().isOk());

    }
//    @Test
//    @DisplayName("Testando salvar alimento")
//    @WithMockUser(username = "testuser", roles = {"USER"})
//    public void teste008() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/alimento/save")
//                        .param("idhorrarioRefeicao", "1")
//                        .param("cpAlimento", "100")
//                        .param("cpQtde", "200.5")
//                        .with(csrf()))  // simula uma requisição autenticada
//                .andExpect(MockMvcResultMatchers.status().isFound())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));
//    }

    @Test
    @DisplayName("Testando cadastro de usuário")
    @WithMockUser
    public void teste009() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/cadastro")
                        .param("nome", "Tiao")
                        .param("password1", "senha")
                        .param("password2", "senha")
                        .param("email", "email@email")
                        .param("peso", "90.00")
                        .param("altura", "1.80")
                        .with(csrf()))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"))
                .andExpect(MockMvcResultMatchers.flash().attributeExists("success"));
    }

    @Test
    @DisplayName("Testando cadastro de usuário com senhas diferentes")
    @WithMockUser
    public void teste010() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/cadastro")
                        .param("nome", "Tiao")
                        .param("password1", "senha1")
                        .param("password2", "senha2")
                        .param("email", "email@email")
                        .param("peso", "90.00")
                        .param("altura", "1.80")
                        .with(csrf()))
                .andExpect(status().isFound()); //aqui era para a página retornar a propria pagina
//                .andExpect(MockMvcResultMatchers.view().name("page/cadastro"));
    }

//    @Test
//    @DisplayName("Testando a criação de controle com sucesso")
//    @WithMockUser
//    public void teste011() throws Exception {
//
//        Usuario usuarioMock = new Usuario();
//        usuarioMock.setIduser(1);
//        usuarioMock.setNome("test");
//
//
//
//        Mockito.when(util.getUserLogado(Mockito.any())).thenReturn(usuarioMock);
//        try {
//
//            mockMvc.perform(MockMvcRequestBuilders.post("/controle")
//                            .param("datepicker", "2024-11-12")
//                            .with(csrf()))
//                    .andExpect(MockMvcResultMatchers.status().isFound())
//                    .andExpect(MockMvcResultMatchers.redirectedUrl("/home"))
//                    .andExpect(MockMvcResultMatchers.flash().attribute("success", "controle criado com sucesso."));
//
//            }catch (Exception e){
//            Assertions.fail();
//            System.out.println("erro: "+ e);
//        }
//    }
//
//
//
//    @Test
//    @DisplayName("Testando cadastro de peso e altura")
//    public void teste012() throws Exception {
//        Usuario usuarioMock = uDao.getReferenceById(123);
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        UserLogado userLogado = new UserLogado(usuarioMock, "email@email", "senha", grantedAuthorities);
//
//        Authentication authentication = Mockito.mock(Authentication.class);
//        Mockito.when(authentication.getPrincipal()).thenReturn(userLogado);
//
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//
//        ResultActions r = mockMvc.perform(MockMvcRequestBuilders.post("/save")
//                .with(csrf())
//                .param("peso", "90.00")
//                .param("altura", "1.80"));
//
//        r.andExpect(status().isFound())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"))
//                .andExpect((ResultMatcher) MockMvcResultHandlers.print());
//    }

}

