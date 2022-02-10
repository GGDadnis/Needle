package org.alterdata.shopback.app.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.alterdata.shopback.app.model.Endereco;
import org.alterdata.shopback.app.model.Recibo;

public class PacienteInserirDTO{
	
	@NotBlank(message = "{nome.not.blank}")
	private String nomePaciente;
	
	@NotBlank(message = "{email.not.blank}")
	private String email;
	
	@NotBlank(message = "{telefone.not.blank}")
	private String telefone;
	
	@NotBlank(message = "{cpf.not.blank}")
	private String cpf;
	
	@NotBlank(message = "{rg.not.blank}")
	private String rg;
	
	@NotNull(message = "A data de nascimento não pode estar em branco!")
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate nascimentoPaciente;
	
	@NotBlank(message = "{mae.not.blank}")
	private String maePaciente;
	
	@NotBlank(message = "{numero.not.blank}")
	private String numero;
	
	@NotBlank(message = "{complemento.not.blank}")
	private String complemento;
	
	private Endereco endereco;
	


	public PacienteInserirDTO() {
	}

	public PacienteInserirDTO(String nomePaciente, String email, String telefone, String cpf, String rg, LocalDate nascimentoPaciente, String maePaciente, String numero, String complemento, Endereco endereco) {
		this.nomePaciente = nomePaciente;
		this.email = email;
		this.telefone = telefone;
		this.cpf = cpf;
		this.rg = rg;
		this.nascimentoPaciente = nascimentoPaciente;
		this.maePaciente = maePaciente;
		this.numero = numero;
		this.complemento = complemento;

	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}	
}
