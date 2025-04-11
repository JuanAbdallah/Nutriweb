package br.com.davesmartins.nutriweb;

import br.com.davesmartins.nutriweb.model.Usuario;
import br.com.davesmartins.nutriweb.repo.UsuarioRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Slf4j
@SpringBootApplication
public class NutriWebNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutriWebNewApplication.class, args);
	}

	@Autowired
	private UsuarioRepo uDao;

	private PasswordEncoder password = new BCryptPasswordEncoder();

	@Bean
	public CommandLineRunner runner() {
		return (args) -> {

//			if (uDao.count()  == 0) {
//				Usuario u = new Usuario(null, "Pedrin",
//						"da@da", password.encode( "123" ),
//						"Adminostrador", null,
//						null);
//
//
//				uDao.save(u);
//
//			}

			log.info("####### Nutri Web - Started #######");
		};

	}


}
