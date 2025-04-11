package br.com.davesmartins.nutriweb.repo;

import br.com.davesmartins.nutriweb.model.ConsumoDiario;
import br.com.davesmartins.nutriweb.model.ConsumoDiarioAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ConsumoDiarioAlimentoRepo extends JpaRepository<ConsumoDiarioAlimento, Integer>{


}
