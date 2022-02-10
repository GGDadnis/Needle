package org.alterdata.shopback.app.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.alterdata.shopback.app.dto.MedicoDTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import static java.lang.Boolean.FALSE;

@Entity
//Faz com que delete coloque true na DB
@SQLDelete(sql = "UPDATE medico SET deleted = true WHERE id_medico=?")
//Faz o get mostrar apenas os que tem false
///@Where(clause = "deleted=false")
@FilterDef(name = "medicoDeletado", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "medicoDeletado", condition = "deleted = :isDeleted")
public class Medico {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Long id;
	
	@Column(name = "nome_medico")
	private String nomeMedico;
	
	@Column(name = "cpf_medico")
	private String cpf;
	
	@Column(name = "crm_medico")
	private String crm;
	
	@Column(name = "profissao_medico")
	private String profissao;
	
	@Column(name = "email_medico")
	private String emailMedico;
	
	@Column(name = "telefone_medico")
	private String telefone;
	
	@Column(name = "nascimento_medico")
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate nascimentoMedico;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_endereco")
    @JsonBackReference
    private Endereco endereco;

	private Boolean deleted = FALSE;

	public Medico() {
		
	}

	public Medico(MedicoDTO medicoDTO) {
		this.nomeMedico = medicoDTO.getNomeMedico();
		this.cpf = medicoDTO.getCpf();
		this.crm = medicoDTO.getCrm();
		this.profissao = medicoDTO.getProfissao();
		this.emailMedico = medicoDTO.getEmailMedico();
		this.telefone = medicoDTO.getTelefone();
		this.nascimentoMedico = medicoDTO.getNascimentoMedico();
		this.endereco = medicoDTO.getEndereco();

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
