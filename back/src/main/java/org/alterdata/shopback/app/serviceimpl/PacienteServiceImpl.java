package org.alterdata.shopback.app.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.alterdata.shopback.app.model.Paciente;
import org.alterdata.shopback.app.dto.PacienteDTO;
import org.alterdata.shopback.app.dto.PacienteInserirDTO;
import org.alterdata.shopback.app.exceptions.NomeException;
import org.alterdata.shopback.app.relatorios.EmissorRecibo;
import org.alterdata.shopback.app.repository.PacienteRepository;
import org.alterdata.shopback.app.service.EnderecoService;
import org.alterdata.shopback.app.service.PacienteService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.validation.OverridesAttribute;

@Service
public class PacienteServiceImpl implements PacienteService{

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private EmissorRecibo emissorRecibo;

	@Autowired
	private EntityManager entityManager;

	@Override
    public List<PacienteDTO> pesquisarTodos(Boolean deletado) {

		Session session =  entityManager.unwrap(Session.class);
		Filter filter = session.enableFilter("pacienteDeletado");
		filter.setParameter("isDeleted",deletado);
		List<Paciente> pacientes = pacienteRepository.findAll();
		session.disableFilter("isDeleted");
 		List<PacienteDTO> pacientesDTO = new ArrayList<PacienteDTO>();

 		for (Paciente paciente : pacientes) {
 			PacienteDTO pacienteDTO = new PacienteDTO(paciente); 
 			pacientesDTO.add(pacienteDTO);
 		}
        return pacientesDTO;
    }
	
	@Override
    public PacienteDTO pesquisarUm(Long id) {
        Optional <Paciente> paciente = pacienteRepository.findById(id);
        if(paciente.isPresent()) {
            PacienteDTO pacienteDTO = new PacienteDTO (paciente.get());
            return pacienteDTO;
            }
        return null;
    }

	@Override
	public PacienteDTO pesquisarUmPorNome(String nome) {
		Optional <Paciente> paciente = Optional.ofNullable(pacienteRepository.findByNomePaciente(nome));
		if(paciente.isPresent()) {
			PacienteDTO pacienteDTO = new PacienteDTO (paciente.get());
			return pacienteDTO;
		}
		return null;
	}
	
	@Override
    public PacienteDTO inserirPaciente(PacienteInserirDTO pacienteInserirDTO) {
        if (pacienteRepository.findByNomePaciente(pacienteInserirDTO.getNomePaciente()) != null) {
            throw new NomeException("Paciente já existe. Verifique o nome ou insira outro."); 
        }
        pacienteInserirDTO.setEndereco(enderecoService.buscar(pacienteInserirDTO.getEndereco().getCep()));
        Paciente paciente = new Paciente(pacienteInserirDTO);
        //aqui mudou o save
        pacienteRepository.save(paciente);
        return new PacienteDTO(paciente);
	}
		
	@Override
	public boolean idExiste(Long id){
		return pacienteRepository.existsById(id);
	}
	
	@Override
	public void removerId(Long id){
		pacienteRepository.deleteById(id);
	}
	
	@Override
	public PacienteDTO atualizarPaciente(String nomePaciente, PacienteInserirDTO pacienteInserirDTO){
		if(pacienteRepository.findByNomePaciente(nomePaciente) == null) {
			throw new NomeException("Paciente não encontrado. Verifique o nome ou insira outro.");
		}
		pacienteInserirDTO.setEndereco(enderecoService.buscar(pacienteInserirDTO.getEndereco().getCep()));
		Paciente paciente = new Paciente(pacienteInserirDTO);
		Paciente id = pacienteRepository.findByNomePaciente(nomePaciente);
		paciente.setId(id.getId());
		pacienteRepository.save(paciente);
		return new PacienteDTO(paciente);
	}
}