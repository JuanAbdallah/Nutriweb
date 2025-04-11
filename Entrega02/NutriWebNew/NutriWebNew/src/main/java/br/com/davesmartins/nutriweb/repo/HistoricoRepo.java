package br.com.davesmartins.nutriweb.repo;

import br.com.davesmartins.nutriweb.model.ConsumoDiario;
import br.com.davesmartins.nutriweb.model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoRepo extends JpaRepository<Historico, Integer>{


    

}
