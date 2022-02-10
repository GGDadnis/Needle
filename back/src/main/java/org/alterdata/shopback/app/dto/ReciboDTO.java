package org.alterdata.shopback.app.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.alterdata.shopback.app.model.Medico;
import org.alterdata.shopback.app.model.Paciente;
import org.alterdata.shopback.app.model.Recibo;

public class ReciboDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Double valor;
	private String servico;
	private LocalDate dataEmissao;
	private String medico;
	private String paciente;

	public ReciboDTO(Recibo recibo) {
		this.id = recibo.getId();
		this.valor = recibo.getValor();
		this.servico = recibo.getServico();
		this.medico = recibo.getNomeMedico();
		this.paciente = recibo.getNomePaciente();
	}

//	public ReciboDTO(String nomeMedico, String nomePaciente, String servico, Double valor) {
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
}
