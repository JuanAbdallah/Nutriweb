package br.com.davesmartins.nutriweb.repo;

import br.com.davesmartins.nutriweb.model.HorrarioRefeicao;
import br.com.davesmartins.nutriweb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HorrarioRefeicaoRepo extends JpaRepository<HorrarioRefeicao, Integer>{

    

}
