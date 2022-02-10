package org.alterdata.shopback.app.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_endereco")
	    private Long id;

	    @Column(name = "cep")
	    private String cep;
	    
	    @Column(name = "logradouro")
	    private String logradouro;

	    @Column(name = "bairro")
	    private String bairro;

	    @Column(name = "cidade")
	    private String localidade;

	    @Column(name = "uf")
	    private String uf;
	    
	    @OneToMany(mappedBy = "endereco", fetch = FetchType.EAGER)
	    @JsonIgnore
	    private List<Paciente> paciente;

		@OneToMany(mappedBy = "endereco", fetch = FetchType.EAGER)
		@JsonIgnore
		private Set<Medico> medico;

	public Endereco() {

	}

	public Endereco(Long id, String cep, String logradouro, String bairro, String localidade, String uf, List<Paciente> paciente, Set<Medico> medico) {
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.paciente = paciente;
		this.medico = medico;
	}

	public String getCep() {
			return cep;
		}

		public void setCep(String cep) {
			this.cep = cep;
		}

		public String getLogradouro() {
			return logradouro;
		}

		public void setLogradouro(String logradouro) {
			this.logradouro = logradouro;
		}

		public String getBairro() {
			return bairro;
		}

		public void setBairro(String bairro) {
			this.bairro = bairro;
		}

		public String getLocalidade() {
			return localidade;
		}

		public void setLocalidade(String localidade) {
			this.localidade = localidade;
		}

		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}

		public String getUf() {
			return uf;
		}

		public void setUf(String uf) {
			this.uf = uf;
		}

		public List<Paciente> getPaciente() {
		return paciente;
		}

		public void setPaciente(List<Paciente> paciente) {
		this.paciente = paciente;
		}

	public Set<Medico> getMedico() {
		return medico;
	}

	public void setMedico(Set<Medico> medico) {
		this.medico = medico;
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
			Endereco other = (Endereco) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
}
