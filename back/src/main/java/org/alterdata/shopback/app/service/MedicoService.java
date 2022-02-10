package org.alterdata.shopback.app.service;

import java.util.List;
import java.util.Optional;

import org.alterdata.shopback.app.dto.MedicoDTO;
import org.alterdata.shopback.app.model.Medico;

public interface MedicoService {

	List<MedicoDTO> pesquisarTodos(boolean deletado);
	boolean idExisteMedico(Long id);
	void removerId(Long id);
    Medico buscar(Long id);
    MedicoDTO inserirMedico(Medico medico);
	MedicoDTO atualizarMedico(String nomeMedico, Medico medico);
}
