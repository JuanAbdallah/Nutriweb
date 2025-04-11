package br.com.davesmartins.nutriweb.repo;

import br.com.davesmartins.nutriweb.model.Alimento;
import br.com.davesmartins.nutriweb.model.ConsumoDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ConsumoDiarioRepo extends JpaRepository<ConsumoDiario, Integer>{


    @Query("select distinct cd.data from ConsumoDiario cd where cd.user.id = :id")
    List<LocalDate> retornaDatas(@Param("id") Integer iduser);

    List<ConsumoDiario> findByUserIduserAndData(Integer iduser, LocalDate dt);

    ConsumoDiario findByUserIduserAndHorrarioRefeicaoIdhorrarioRefeicaoAndData(Integer iduser, Integer idhorrarioRefeicao, LocalDate data);
}
