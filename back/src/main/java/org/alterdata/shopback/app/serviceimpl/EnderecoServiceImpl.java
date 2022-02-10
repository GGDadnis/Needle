package org.alterdata.shopback.app.serviceimpl;

import java.util.Optional;

import org.alterdata.shopback.app.dto.EnderecoDTO;
import org.alterdata.shopback.app.model.Endereco;
import org.alterdata.shopback.app.repository.EnderecoRepository;
import org.alterdata.shopback.app.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoServiceImpl implements EnderecoService{

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public Endereco buscar(String cep) throws HttpClientErrorException{
		Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cep));
		if (endereco.isPresent()) {
			return endereco.get();
		} else {
			RestTemplate restTemplate = new RestTemplate();
			String uriViaCep = "https://viacep.com.br/ws/" + cep + "/json/";
			Optional<Endereco> enderecoViaCep = Optional.ofNullable(restTemplate.getForObject(uriViaCep, Endereco.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replace("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				return inserirEndereco(enderecoViaCep.get());
			} else {
				return null;
			}
		}
	}

	public Endereco inserirEndereco(Endereco endereco) {
		endereco = enderecoRepository.save(endereco);
		return endereco;
	}
}
