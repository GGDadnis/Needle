package org.alterdata.shopback.app.repository;

import java.util.List;

import org.alterdata.shopback.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Role findByNome(String nome);
}
