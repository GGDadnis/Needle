package org.alterdata.shopback.app.relatorios;

import lombok.Builder;
import org.alterdata.shopback.app.model.enums.TipoLayoutReciboEnum;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Builder
public class ReciboRelatorio implements TemplateRelatorio {

    private static final String TEMPLATE_MODELO_1 = "/templates/recibos/recibo_model1.jasper";
    private static final String TEMPLATE_MODELO_2 = "/templates/recibos/recibo_model2.jasper";
    private boolean exibeNomeMedico;
    private boolean exibeNomePaciente;
    private boolean exibeServico;
    private boolean exibeValor;
    private String nomeMedico;
    private String nomePaciente;
    private String servico;
    private Double valor;
    private TipoLayoutReciboEnum tipoLayout;

    @Override
    public InputStream arquivoRelatorio() {

        if(TipoLayoutReciboEnum.LAYOUT_2.equals(tipoLayout)){
            return getClass().getResourceAsStream(TEMPLATE_MODELO_2);
        }
        // Caso não seja informado um layout, será selecionado o Layout 1.
        return getClass().getResourceAsStream(TEMPLATE_MODELO_1);
    }

    @Override
    public Map<String, Object> parametros() {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("exibeNomeMedico", exibeNomeMedico);
        parametros.put("exibeNomePaciente", exibeNomePaciente);
        parametros.put("exibeServico", exibeServico);
        parametros.put("exibeValor", exibeValor);
        parametros.put("nomeMedico", nomeMedico);
        parametros.put("nomePaciente", nomePaciente);
        parametros.put("servico", servico);
        parametros.put("valor", valor);
        return parametros;
    }

    @Override
    public Collection dadosRecibo() {
        return null;
    }
}
