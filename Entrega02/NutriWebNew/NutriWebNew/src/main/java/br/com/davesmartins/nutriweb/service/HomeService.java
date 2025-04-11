package br.com.davesmartins.nutriweb.service;

import br.com.davesmartins.nutriweb.model.*;
import br.com.davesmartins.nutriweb.model.dto.CadastroUsuarioDTO;
import br.com.davesmartins.nutriweb.repo.*;
import br.com.davesmartins.nutriweb.util.LogadoUtil;
import br.com.davesmartins.nutriweb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class HomeService {

	@Autowired
	LogadoUtil util;

	@Autowired
		private UsuarioRepo uDao;

	@Autowired
	private ConsumoDiarioRepo cdDao;

	@Autowired
	private AlimentoRepo aliDao;

	@Autowired
	private ConsumoDiarioAlimentoRepo cdaDao;


	public List<LocalDate> getDatas(Usuario u) {

		return cdDao.retornaDatas(u.getIduser());

	}

	public String getDataGrafico(Usuario user) {

		String resp="";
		for (Historico h:
			 user.getHistoricoList()) {
			resp += "['"+h.getDataMedida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"'," +
					h.getPeso()+","+h.imc()+"],";
		}

		return resp;
	}

	public List<ConsumoDiario> getListaConsumo(Usuario user, LocalDate dt) {

		return cdDao.findByUserIduserAndData(user.getIduser(),dt);
	}

	public double totalConsumo(Usuario user) {
		double total = 0;
		for (ConsumoDiario cd :
				user.getConsumoDiarioList()) {
			total += cd.totalCalMax();
		}
		return total;
	}

	public double totalCalMax(Usuario user) {
		double total = 0;
		for (ConsumoDiario cd :
				user.getConsumoDiarioList()) {
			total += cd.getCalMax();
		}
		return total;
	}

	public List<Alimento> listaAlimentos() {
		return aliDao.findAll();
	}

	@Transactional
	public void saveAlimentos(Usuario user, LocalDate data, Integer idhorrarioRefeicao, Integer cpAlimento, double qtde) {

		user = uDao.getReferenceById(user.getIduser());
		ConsumoDiario cd = cdDao.findByUserIduserAndHorrarioRefeicaoIdhorrarioRefeicaoAndData(user.getIduser(),idhorrarioRefeicao,data);
		Alimento ali = aliDao.getById(cpAlimento);

		ConsumoDiarioAlimento cda = new ConsumoDiarioAlimento(null,qtde, cd,ali);
//		cda.setConsumoDiario(cd);
//		cda.setQtde(qtde);
//		cda.setAlimento(ali);
//		cd.getConsumoDiarioAlimentoList().add(cda);

		cdaDao.save(cda);

	}


//	 @Autowired
//	 private UsuarioRepo uDao;
//	@Autowired
//	private HorrarioRefeicaoRepo hDao;
//	@Autowired
//	private ConsumoDiarioRepo cDao;
//
//	@Autowired
//	private HistoricoRepo hisDao;


}
