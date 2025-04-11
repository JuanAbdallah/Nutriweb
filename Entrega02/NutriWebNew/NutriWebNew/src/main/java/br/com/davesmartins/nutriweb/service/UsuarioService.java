package br.com.davesmartins.nutriweb.service;

import javax.validation.Valid;

import br.com.davesmartins.nutriweb.model.*;
import br.com.davesmartins.nutriweb.model.dto.CadastroUsuarioDTO;
import br.com.davesmartins.nutriweb.model.dto.UsuarioPesoDTO;
import br.com.davesmartins.nutriweb.repo.ConsumoDiarioRepo;
import br.com.davesmartins.nutriweb.repo.HistoricoRepo;
import br.com.davesmartins.nutriweb.repo.HorrarioRefeicaoRepo;
import br.com.davesmartins.nutriweb.repo.UsuarioRepo;
import br.com.davesmartins.nutriweb.util.LogadoUtil;
import br.com.davesmartins.nutriweb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class UsuarioService {

	
	 @Autowired
	 private UsuarioRepo uDao;
	@Autowired
	private HorrarioRefeicaoRepo hDao;
	@Autowired
	private HistoricoRepo histDao;
	@Autowired
	private ConsumoDiarioRepo cDao;

	@Autowired
	private HistoricoRepo hisDao;

	@Autowired
	LogadoUtil util;


	@Transactional
	public void save(@Valid CadastroUsuarioDTO usuario) {
		
		Usuario user = usuario.ConvertUsuario();

		user = uDao.save(user);


		user.getHistoricoList().add(Historico.builder()
				.altura(usuario.getAltura())
				.peso(usuario.getPeso())
				.dataMedida(LocalDate.now())
				.user(user)
				.build());

		hisDao.saveAll(user.getHistoricoList());

		gravaComsumo(user);

		uDao.save(user);
		
	}

	@Transactional
	public void gravaComsumo(Usuario user) {
		gravaComsumo(user,LocalDate.now());
	}


	@Transactional
	public void gravaComsumo(Usuario user, LocalDate DataConsumo) {
		List<HorrarioRefeicao> listaHor = hDao.findAll();

		for (HorrarioRefeicao hr : listaHor) {
			RefeicoesObjetivo ro = Util.findDieta(user.getObjetivo(), hr.getDescricao());
			if (ro != null) {
				ConsumoDiario cd = new ConsumoDiario();
//						new ConsumoDiario(new ConsumoDiarioPK(user.getIduser(), hr.getIdhorrarioRefeicao(),
//						LocalDate.now()));
				cd.setUser(user);
				cd.setData(DataConsumo);
				cd.setHorrarioRefeicao(hr);
				cd.setHorario(ro.getHorario());
				cd.setCalMax(ro.getCalMax());
				cd.setConsumoDiarioAlimentoList(new ArrayList<>());

				cDao.save(cd);
				//hr.getConsumoDiarioList().add(cd);
				user.getConsumoDiarioList().add(cd);
			}
		}
	}

	@Transactional
	public void changeObjetivo(Usuario user, String objetivo) {

		user.setObjetivo(objetivo);

		uDao.save(user);

	}

	public void gravaHistorico(Usuario user, UsuarioPesoDTO usuario) {

		Historico hist = Historico.builder().user(user)
				.peso(usuario.getPeso())
				.altura(usuario.getAltura())
				.dataMedida(LocalDate.now())
				.build();
		histDao.save(hist);
		user.getHistoricoList().add(hist);
		uDao.save(user);
	}
}
