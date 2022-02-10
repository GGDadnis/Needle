package org.alterdata.shopback.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.alterdata.shopback.app.model.enums.TipoLayoutReciboEnum;

import javax.persistence.Enumerated;
import java.io.Serializable;

public class ReciboTemplateDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nomeMedico;
    private String nomePaciente;
    private String servico;
    private Double valor;

    @JsonProperty("exibicaoCampos")
    private ExibicaoCamposDTO exibicaoCamposDTO;

    @JsonProperty("tipoLayout")
    @Enumerated
    private TipoLayoutReciboEnum tipoLayoutReciboEnum;

    public ReciboTemplateDTO() {
    }

    public ReciboTemplateDTO(String nomeMedico, String nomePaciente, String servico, Double valor, ExibicaoCamposDTO exibicaoCamposDTO, TipoLayoutReciboEnum tipoLayoutReciboEnum) {
        this.nomeMedico = nomeMedico;
        this.nomePaciente = nomePaciente;
        this.servico = servico;
        this.valor = valor;
        this.exibicaoCamposDTO = exibicaoCamposDTO;
        this.tipoLayoutReciboEnum = tipoLayoutReciboEnum;
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

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public ExibicaoCamposDTO getExibicaoCamposDTO() {
        return exibicaoCamposDTO;
    }

    public void setExibicaoCamposDTO(ExibicaoCamposDTO exibicaoCamposDTO) {
        this.exibicaoCamposDTO = exibicaoCamposDTO;
    }

    public TipoLayoutReciboEnum getTipoLayoutReciboEnum() {
        return tipoLayoutReciboEnum;
    }

    public void setTipoLayoutReciboEnum(TipoLayoutReciboEnum tipoLayoutReciboEnum) {
        this.tipoLayoutReciboEnum = tipoLayoutReciboEnum;
    }
}
