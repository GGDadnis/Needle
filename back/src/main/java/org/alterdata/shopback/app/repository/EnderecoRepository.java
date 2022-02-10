package org.alterdata.shopback.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.alterdata.shopback.app.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	public Endereco findByCep(String cep);
}
