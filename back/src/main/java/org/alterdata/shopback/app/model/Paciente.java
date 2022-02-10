package org.alterdata.shopback.app.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.alterdata.shopback.app.dto.PacienteInserirDTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.*;

import static java.lang.Boolean.FALSE;

@Entity
//Faz com que delete coloque true na DB
@SQLDelete(sql = "UPDATE paciente SET deleted = true WHERE id_paciente=?")
//Faz o get mostrar apenas os que tem false
///@Where(clause = "deleted=false")
@FilterDef(name = "pacienteDeletado", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "pacienteDeletado", condition = "deleted = :isDeleted")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paciente")
	private Long id;

	@Column(name = "nome_paciente")
	private String nomePaciente;

	@Column(name = "email_paciente")
	private String email;

	@Column(name = "telefone_paciente")
	private String telefone;

	@Column(name = "cpf_paciente")
	private String cpf;

	@Column(name = "rg_paciente")
	private String rg;

	@Column(name = "nascimento_paciente")
	@JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate nascimentoPaciente;

	@Size(max = 50)
	@Column(name = "mae_paciente")
	private String maePaciente;

	@Column(name = "numero_endereco")
	private String numero;

	@Column(name = "complemento")
	private String complemento;

	private Boolean deleted = FALSE;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_endereco")
	@JsonBackReference
	private Endereco endereco;

	public Paciente() {

	}
	
	public Paciente(PacienteInserirDTO pacienteInserirDTO) {
		this.nomePaciente = pacienteInserirDTO.getNomePaciente();
		this.email = pacienteInserirDTO.getEmail();
		this.telefone = pacienteInserirDTO.getTelefone();
		this.cpf = pacienteInserirDTO.getCpf();
		this.rg = pacienteInserirDTO.getRg();
		this.nascimentoPaciente = pacienteInserirDTO.getNascimentoPaciente();
		this.maePaciente = pacienteInserirDTO.getMaePaciente();
		this.numero = pacienteInserirDTO.getNumero();
		this.complemento = pacienteInserirDTO.getComplemento();
		this.endereco = pacienteInserirDTO.getEndereco();
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Boolean getDeletado() {
		return deleted;
	}

	public void setDeletado(Boolean deleted) {
		this.deleted = deleted;
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
		Paciente other = (Paciente) obj;
		return Objects.equals(id, other.id);
	}

}
