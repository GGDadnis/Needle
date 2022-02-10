package org.alterdata.shopback.app.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.alterdata.shopback.app.dto.MedicoDTO;
import org.alterdata.shopback.app.exceptions.IdException;
import org.alterdata.shopback.app.exceptions.NomeException;
import org.alterdata.shopback.app.model.Medico;
import org.alterdata.shopback.app.repository.MedicoRepository;
import org.alterdata.shopback.app.service.EnderecoService;
import org.alterdata.shopback.app.service.MedicoService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class MedicoServiceImpl implements MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<MedicoDTO> pesquisarTodos(boolean deletado) {
		Session session =  entityManager.unwrap(Session.class);
		Filter filter = session.enableFilter("medicoDeletado");
		filter.setParameter("isDeleted",deletado);
		List<Medico> medicos = medicoRepository.findAll();
		session.disableFilter("isDeleted");
		List<MedicoDTO> medicosDTO = new ArrayList<MedicoDTO>();

		for (Medico medico : medicos) {
			MedicoDTO medicoDTO = new MedicoDTO(medico);
			medicosDTO.add(medicoDTO);
		}
		return medicosDTO;
	}

	@Override
	public Medico buscar(Long id){
		if(!idExisteMedico(id)) {
			throw new IdException("Médico não encontrado.");
		}
		return medicoRepository.getById(id);
	}

	@Override
	public MedicoDTO inserirMedico(Medico medico) {
		if (medicoRepository.findByNomeMedico(medico.getNomeMedico()) != null) {
			throw new NomeException("Médico já existe. Verifique o nome ou insira outro.");
		}
		medico.setEndereco(enderecoService.buscar(medico.getEndereco().getCep()));
		medicoRepository.save(medico);
		return new MedicoDTO(medico);
	}

	@Override
	public boolean idExisteMedico(Long id) {
		return medicoRepository.existsById(id);
	}

	@Override
	public void removerId(Long id) {
		medicoRepository.deleteById(id);
	}

	@Override
	public MedicoDTO atualizarMedico(String nomeMedico, Medico medico) {
		if(medicoRepository.findByNomeMedico(nomeMedico) == null) {
			throw new NomeException("Médico não encontrado. Verifique o nome ou insira outro.");
		}
		medico.setEndereco(enderecoService.buscar(medico.getEndereco().getCep()));
		MedicoDTO id = medicoRepository.findByNomeMedico(nomeMedico);
		medico.setId(id.getId());
		medicoRepository.save(medico);
		return new MedicoDTO(medico);
	}
}
