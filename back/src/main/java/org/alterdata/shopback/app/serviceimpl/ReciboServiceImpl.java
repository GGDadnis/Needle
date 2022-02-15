package org.alterdata.shopback.app.serviceimpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.alterdata.shopback.app.dto.*;
import org.alterdata.shopback.app.exceptions.IdException;
import org.alterdata.shopback.app.model.Paciente;
import org.alterdata.shopback.app.model.Recibo;
import org.alterdata.shopback.app.relatorios.EmissorRecibo;
import org.alterdata.shopback.app.repository.MedicoRepository;
import org.alterdata.shopback.app.repository.PacienteRepository;
import org.alterdata.shopback.app.repository.ReciboRepository;
import org.alterdata.shopback.app.service.ReciboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReciboServiceImpl implements ReciboService {

	@Autowired
	private ReciboRepository reciboRepository;

	@Autowired
	EmissorRecibo emissorRecibo;

	@Autowired
	MedicoRepository medicoRepository;

	@Autowired
	PacienteRepository pacienteRepository;
	
	// Autowired
//	private MailConfig mailConfig;

	@Override
	public List<ReciboDTO> listar() {
		List<Recibo> recibos = reciboRepository.findAll();
		List<ReciboDTO> recibosDTO = new ArrayList<>();

		for (Recibo recibo : recibos) {
			ReciboDTO reciboDTO = new ReciboDTO(recibo);
			recibosDTO.add(reciboDTO);
		}
		return recibosDTO;
	}

	@Override
	public Optional<Recibo> buscar(Long id){
		if(!idExisteRecibo(id)) {
			throw new IdException("Recibo não encontrado.");
		}
		return reciboRepository.findById(id);
	}

	@Override
	public ReciboDTO inserirRecibo(Recibo recibo) {

		reciboRepository.save(recibo);
		return new ReciboDTO(recibo);
	}
	
	@Override
	public boolean idExisteRecibo(Long id) {
		return reciboRepository.existsById(id);		
	}

	@Override
	public InputStream exportarPdf(ReciboTemplateDTO reciboTemplateDTO){
		Recibo recibo = new Recibo(reciboTemplateDTO);
		MedicoDTO med = medicoRepository.findByNomeMedico(recibo.getNomeMedico());
		Paciente pac = pacienteRepository.findByNomePaciente(recibo.getNomePaciente());
		if(pac == null || med == null){
			throw new NullPointerException("um dos campos esta vazio");
		}
		reciboRepository.save(recibo);
		return emissorRecibo.emitirRelatorio(reciboTemplateDTO);
	}
	
}
