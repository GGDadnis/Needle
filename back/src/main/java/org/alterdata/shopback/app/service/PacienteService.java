package org.alterdata.shopback.app.service;

import java.io.InputStream;
import java.util.List;

import org.alterdata.shopback.app.dto.PacienteDTO;
import org.alterdata.shopback.app.dto.PacienteInserirDTO;

public interface PacienteService {
	List<PacienteDTO> pesquisarTodos(Boolean isDeleted);
	PacienteDTO pesquisarUm(Long id);
	void removerId(Long id);
	boolean idExiste(Long id);
	PacienteDTO inserirPaciente(PacienteInserirDTO pacienteInserirDTO);
	PacienteDTO atualizarPaciente(String Paciente, PacienteInserirDTO pacienteInserirDTO);
	public PacienteDTO pesquisarUmPorNome(String nome);

}
