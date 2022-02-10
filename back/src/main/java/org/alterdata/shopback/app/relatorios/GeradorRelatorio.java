package org.alterdata.shopback.app.relatorios;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class GeradorRelatorio {

    private TemplateRelatorio relatorio;

    public GeradorRelatorio(TemplateRelatorio relatorio) {
        this.relatorio = relatorio;
    }

    public InputStream exportarPdf() throws JRException {
        JasperReport template = (JasperReport) JRLoader.loadObject(relatorio.arquivoRelatorio());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(relatorio.dadosRecibo());
        JasperPrint jasperPrint = JasperFillManager.fillReport(template, relatorio.parametros(), new JREmptyDataSource());
        return new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jasperPrint));
    }

}
