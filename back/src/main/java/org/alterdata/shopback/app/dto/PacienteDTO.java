package org.alterdata.shopback.app.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.alterdata.shopback.app.model.Paciente;
import org.alterdata.shopback.app.model.Recibo;
import org.alterdata.shopback.app.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.alterdata.shopback.app.model.Endereco;


public class PacienteDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nomePaciente;
	private String email;
	private String telefone;
	private String cpf;
	private String rg;
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate nascimentoPaciente;
	private String maePaciente;
	private String numero;
	private String complemento;
	private Endereco endereco;

	
	@Autowired
	EnderecoService enderecoService;
	
	public PacienteDTO() {
		
	}

	public PacienteDTO(Paciente paciente) {
		this.id = paciente.getId();
		this.nomePaciente = paciente.getNomePaciente();
		this.email = paciente.getEmail();
		this.telefone = paciente.getTelefone();
		this.cpf= paciente.getCpf();
		this.rg = paciente.getRg();
		this.nascimentoPaciente = paciente.getNascimentoPaciente();
		this.maePaciente = paciente.getMaePaciente();
		this.numero = paciente.getNumero();
		this.complemento = paciente.getComplemento();
		this.endereco = paciente.getEndereco();

		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public LocalDate getNascimentoPaciente() {
		return nascimentoPaciente;
	}

	public void setNascimentoPaciente(LocalDate nascimentoPaciente) {
		this.nascimentoPaciente = nascimentoPaciente;
	}

	public String getMaePaciente() {
		return maePaciente;
	}

	public void setMaePaciente(String maePaciente) {
		this.maePaciente = maePaciente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
