package org.alterdata.shopback.app.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.alterdata.shopback.app.dto.ReciboDTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.alterdata.shopback.app.dto.ReciboTemplateDTO;

@Entity
public class Recibo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recibo")
    private Long id;
	
	@Column(name = "valor_recibo")
	private Double valor;
	
	@Column(name = "servico_recibo")
	@NotBlank(message = "algum serviço deve ser realizado")
	private String servico;

	private String nomeMedico;

	private String nomePaciente;
	
	public Recibo() {
		
	}

	public Recibo(ReciboTemplateDTO reciboDTO) {
		this.valor = reciboDTO.getValor();
		this.servico = reciboDTO.getServico();
		this.nomeMedico = reciboDTO.getNomeMedico();
		this.nomePaciente = reciboDTO.getNomePaciente();
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
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

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
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
		Recibo other = (Recibo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
