package org.alterdata.shopback.app.relatorios;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;


public interface TemplateRelatorio {

    InputStream arquivoRelatorio();
    Map<String, Object> parametros();
    Collection dadosRecibo();



}
