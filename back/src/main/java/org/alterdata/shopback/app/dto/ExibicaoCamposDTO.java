package org.alterdata.shopback.app.dto;

import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Objects;

public class ExibicaoCamposDTO implements Serializable {
    private boolean exibeNomeMedico;
    private boolean exibeNomePaciente;
    private boolean exibeServico;
    private boolean exibeValor;

    public ExibicaoCamposDTO() {
    }

    public ExibicaoCamposDTO(boolean exibeNomeMedico, boolean exibeNomePaciente, boolean exibeServico, boolean exibeValor) {
        this.exibeNomeMedico = exibeNomeMedico;
        this.exibeNomePaciente = exibeNomePaciente;
        this.exibeServico = exibeServico;
        this.exibeValor = exibeValor;
    }

    public boolean isExibeNomeMedico() {
        return exibeNomeMedico;
    }

    public void setExibeNomeMedico(boolean exibeNomeMedico) {
        this.exibeNomeMedico = exibeNomeMedico;
    }

    public boolean isExibeNomePaciente() {
        return exibeNomePaciente;
    }

    public void setExibeNomePaciente(boolean exibeNomePaciente) {
        this.exibeNomePaciente = exibeNomePaciente;
    }

    public boolean isExibeServico() {
        return exibeServico;
    }

    public void setExibeServico(boolean exibeServico) {
        this.exibeServico = exibeServico;
    }

    public boolean isExibeValor() {
        return exibeValor;
    }

    public void setExibeValor(boolean exibeValor) {
        this.exibeValor = exibeValor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExibicaoCamposDTO that = (ExibicaoCamposDTO) o;
        return exibeNomeMedico == that.exibeNomeMedico && exibeNomePaciente == that.exibeNomePaciente && exibeServico == that.exibeServico && exibeValor == that.exibeValor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exibeNomeMedico, exibeNomePaciente, exibeServico, exibeValor);
    }
}
