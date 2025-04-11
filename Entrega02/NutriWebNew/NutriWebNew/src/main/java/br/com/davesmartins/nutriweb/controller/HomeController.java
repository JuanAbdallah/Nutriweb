package br.com.davesmartins.nutriweb.controller;

import br.com.davesmartins.nutriweb.model.ConsumoDiario;
import br.com.davesmartins.nutriweb.model.Usuario;
import br.com.davesmartins.nutriweb.model.dto.CadastroUsuarioDTO;
import br.com.davesmartins.nutriweb.model.dto.UsuarioPesoDTO;
import br.com.davesmartins.nutriweb.service.HomeService;
import br.com.davesmartins.nutriweb.service.UsuarioService;
import br.com.davesmartins.nutriweb.util.LogadoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


@Controller
public class HomeController {

    @Autowired
    LogadoUtil util;

    @Autowired
    UsuarioService uService;

    @Autowired
    HomeService hService;

    @Autowired
    private PasswordEncoder password;

        
    
    @RequestMapping(path = {"/", "/home"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String home(@ModelAttribute("usuarioPeso") UsuarioPesoDTO usuario, Authentication authentication, ModelMap model,
                       HttpSession sessao,String cpDataFiltro) {

        if (authentication == null){
            return "page/home";
        }

      if (authentication.isAuthenticated()){
          Usuario user = util.getUserLogado(authentication);
          model.addAttribute("imc",  new DecimalFormat("#0.00").format(user.imc()) );
          List<LocalDate> datas = hService.getDatas(user);
          model.addAttribute("ListaDatas", datas);
          model.addAttribute("dGra1",  hService.getDataGrafico(user));
          List<ConsumoDiario> listaConsumo = null;
          if (cpDataFiltro != null && !cpDataFiltro.equals("")){
              sessao.setAttribute("dataFiltro",LocalDate.parse(cpDataFiltro, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
              listaConsumo = hService.getListaConsumo(user,  LocalDate.parse(cpDataFiltro, DateTimeFormatter.ofPattern("yyyy-MM-dd"))  );
          }else{
              sessao.setAttribute("dataFiltro",datas.get(0));
              listaConsumo = hService.getListaConsumo(user, datas.get(0));
          }
          model.addAttribute("dataFiltro",   sessao.getAttribute("dataFiltro")==null? datas.get(0):sessao.getAttribute("dataFiltro"));
//          List<ConsumoDiario> listaConsumo = hService.getListaConsumo(user,  LocalDate.parse(cpDataFiltro) datas.get(0));
          model.addAttribute("ListaconsumoDiario", listaConsumo);
          model.addAttribute("totalConsumo", hService.totalConsumo(user));
          model.addAttribute("totalCalMax", hService.totalCalMax(user));
          usuario.setAltura(user.getHistoricoList().get( user.getHistoricoList().size()-1 ).getAltura());
          usuario.setPeso(user.getHistoricoList().get( user.getHistoricoList().size()-1 ).getPeso());
      }

      return "page/home";
    }

    @GetMapping("/alimento/view")
    public String alimento( Authentication authentication, ModelMap model) {
        model.addAttribute("listaAlimentos", hService.listaAlimentos());
        return "page/ViewAlimento";
    }

    @PostMapping("/alimento/save")
    public String alimentoSave( Authentication authentication, ModelMap model,
                                @RequestParam("idhorrarioRefeicao") String idhorrarioRefeicao,
                                @RequestParam("cpAlimento") String cpAlimento,
                                @RequestParam("cpQtde") String cpQtde,
                                HttpSession sessao,
                                RedirectAttributes attr) {

        Usuario user = util.getUserLogado(authentication);
        hService.saveAlimentos(user,
                (LocalDate)sessao.getAttribute("dataFiltro"),
                Integer.parseInt(idhorrarioRefeicao) ,
                Integer.parseInt(cpAlimento) ,
                Double.parseDouble(cpQtde) );

        attr.addFlashAttribute("success", "alimento cadastrado com sucesso.");

        return "redirect:/home";
    }


    @GetMapping("/about")
    public String about() {
        return "page/ajuda";
    }

    @GetMapping("/cadastro")
    public String cadastro(@ModelAttribute("usuario") CadastroUsuarioDTO usuario,
                           ModelMap model) {
        return "page/cadastro";
    }

    @PostMapping("/cadastro")
    public String saveUser(@Valid @ModelAttribute("usuario") CadastroUsuarioDTO usuario, BindingResult result,
                           RedirectAttributes attr, ModelMap model, Authentication authentication) {

        if (result.hasErrors()) {
            return "page/cadastro";
        }

        usuario.setPassword1( password.encode( usuario.getPassword1() ) );

        uService.save(usuario);

        attr.addFlashAttribute("success", "cadastro criado com sucesso.");

        return "redirect:/home";
    }
    @PostMapping("/save")
    public String savePeso(@Valid @ModelAttribute("usuarioPeso") UsuarioPesoDTO usuario, BindingResult result,
                           RedirectAttributes attr, ModelMap model, Authentication authentication) {

        if (result.hasErrors()) {
            return "page/home";
        }
        Usuario user = util.getUserLogado(authentication);
        uService.gravaHistorico(user, usuario);


        attr.addFlashAttribute("success", "dados adicionado com sucesso.");

        return "redirect:/home";
    }


    @PostMapping("/controle")
    public String saveControle(@RequestParam("datepicker") String dt, ModelMap model,
                           RedirectAttributes attr, Authentication authentication) {

        uService.gravaComsumo(util.getUserLogado(authentication), LocalDate.parse(dt, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        attr.addFlashAttribute("success", "controle criado com sucesso. ");

        return "redirect:/home";
    }



    @GetMapping("/login")
    public String login() {
        return "page/home";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("msgError", "Login ou senha incorreta");
        return "page/home";
    }

}
