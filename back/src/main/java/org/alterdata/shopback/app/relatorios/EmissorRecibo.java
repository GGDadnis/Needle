package org.alterdata.shopback.app.relatorios;

import net.sf.jasperreports.engine.JRException;
import org.alterdata.shopback.app.dto.MedicoDTO;
import org.alterdata.shopback.app.dto.ReciboTemplateDTO;
import org.alterdata.shopback.app.model.Paciente;
import org.alterdata.shopback.app.model.enums.TipoLayoutReciboEnum;
import org.alterdata.shopback.app.repository.MedicoRepository;
import org.alterdata.shopback.app.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class EmissorRecibo {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    MedicoRepository medicoRepository;

    public InputStream emitirRelatorio(ReciboTemplateDTO reciboTemplateDTO) {

        MedicoDTO medico = medicoRepository.findByNomeMedico(reciboTemplateDTO.getNomeMedico());
        if(medico == null){
            throw new NullPointerException("Médico não encontrado!");
        }

        Paciente paciente = pacienteRepository.findByNomePaciente(reciboTemplateDTO.getNomePaciente());
        if(paciente == null){
            throw new NullPointerException("Paciente não encontrado!");
        }

        ReciboRelatorio reciboRelatorio = ReciboRelatorio.builder()
                .exibeNomeMedico(reciboTemplateDTO.getExibicaoCamposDTO().isExibeNomeMedico())
                .exibeNomePaciente(reciboTemplateDTO.getExibicaoCamposDTO().isExibeNomePaciente())
                .exibeServico(reciboTemplateDTO.getExibicaoCamposDTO().isExibeServico())
                .exibeValor(reciboTemplateDTO.getExibicaoCamposDTO().isExibeValor())
                .tipoLayout(TipoLayoutReciboEnum.getTipoLayout(reciboTemplateDTO.getTipoLayoutReciboEnum().getTipo()))
                .nomeMedico(medico.getNomeMedico())
                .nomePaciente(paciente.getNomePaciente())
                .servico(reciboTemplateDTO.getServico())
                .valor(reciboTemplateDTO.getValor())
                .build();

        GeradorRelatorio gerador = new GeradorRelatorio(reciboRelatorio);
        try {
            return gerador.exportarPdf();
        } catch (JRException e) {
            e.printStackTrace();
            return null;
        }
    }
}
