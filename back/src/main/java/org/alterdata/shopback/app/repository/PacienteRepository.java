package org.alterdata.shopback.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.alterdata.shopback.app.dto.PacienteDTO;
import org.alterdata.shopback.app.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	public Paciente findByNomePaciente(String nomePaciente);
	public PacienteDTO save(PacienteDTO pac);
}
