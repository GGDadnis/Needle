package org.alterdata.shopback.app.service;
import org.alterdata.shopback.app.model.Endereco;

public interface EnderecoService {
	
	Endereco buscar(String cep);
	Endereco inserirEndereco(Endereco endereco);


}
