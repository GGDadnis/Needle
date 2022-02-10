package org.alterdata.shopback.app.repository;

import org.alterdata.shopback.app.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.alterdata.shopback.app.model.Recibo;

public interface ReciboRepository extends JpaRepository<Recibo, Long>{
	public Optional<Recibo> findById(Long id);
}
