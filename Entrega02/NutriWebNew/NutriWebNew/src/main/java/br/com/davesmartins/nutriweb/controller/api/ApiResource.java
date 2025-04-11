package br.com.davesmartins.nutriweb.controller.api;

import br.com.davesmartins.nutriweb.config.security.user.UserLogado;
import br.com.davesmartins.nutriweb.model.Usuario;
import br.com.davesmartins.nutriweb.service.UsuarioService;
import br.com.davesmartins.nutriweb.util.LogadoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiResource {

    @Autowired
    UsuarioService uService;

	@Autowired
	LogadoUtil util;

	@GetMapping("/change/{obj}")
	public ResponseEntity<String> change(@PathVariable  String obj, Authentication authentication) {

		((Usuario)((UserLogado)authentication.getPrincipal()).getUser()).setObjetivo(obj);

//		authentication.getPrincipal().setUser().setObjetivo(obj)
		    uService.changeObjetivo(util.getUserLogado(authentication), obj);
            
            return ResponseEntity.ok("");

	}

	
}
