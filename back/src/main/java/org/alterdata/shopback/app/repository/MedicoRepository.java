package org.alterdata.shopback.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.alterdata.shopback.app.dto.MedicoDTO;
import org.alterdata.shopback.app.model.Medico;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
	public MedicoDTO findByNomeMedico(String nomeMedico);
	public Medico findByEmailMedico(String email);
	public MedicoDTO save(MedicoDTO medicoDTO);
}
