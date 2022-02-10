package org.alterdata.shopback.app.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.alterdata.shopback.app.model.Endereco;
import org.alterdata.shopback.app.model.Medico;
import org.alterdata.shopback.app.model.Recibo;
import org.alterdata.shopback.app.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;

public class MedicoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "{nome.not.blank}")
	private String nomeMedico;
	
	@NotBlank(message = "{cpf.not.blank}")
	private String cpf;
	
	@NotBlank(message = "{crm.not.blank}")
	private String crm;
	
	@NotBlank(message = "{profissao.not.blank}")
	private String profissao;
	
	@NotBlank(message = "{email.not.blank}")
	private String emailMedico;
	
	@NotBlank(message = "{telefone.not.blank}")
	private String telefone;

	@NotNull(message = "A data de nascimento não pode estar em branco!")
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate nascimentoMedico;
	
	private Endereco endereco;
	

	
	@Autowired
	EnderecoService enderecoService;
	
	public MedicoDTO() {
	}

	public MedicoDTO(Medico medico) {
		this.id = medico.getId();
		this.nomeMedico = medico.getNomeMedico();
		this.cpf = medico.getCpf();
		this.crm = medico.getCrm();
		this.profissao = medico.getProfissao();
		this.emailMedico = medico.getEmailMedico();
		this.telefone = medico.getTelefone();
		this.nascimentoMedico = medico.getNascimentoMedico();
		this.endereco = medico.getEndereco();

	}
	
	public Long getId() {
			return id;
		}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getEmailMedico() {
		return emailMedico;
	}

	public void setEmailMedico(String emailMedico) {
		this.emailMedico = emailMedico;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getNascimentoMedico() {
		return nascimentoMedico;
	}

	public void setNascimentoMedico(LocalDate nascimentoMedico) {
		this.nascimentoMedico = nascimentoMedico;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


}
