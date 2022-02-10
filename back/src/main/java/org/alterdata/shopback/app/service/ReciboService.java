package org.alterdata.shopback.app.service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.alterdata.shopback.app.dto.ExibicaoCamposDTO;
import org.alterdata.shopback.app.dto.ReciboDTO;
import org.alterdata.shopback.app.dto.ReciboTemplateDTO;
import org.alterdata.shopback.app.model.Recibo;

public interface ReciboService {
	List<ReciboDTO> listar();
	Optional<Recibo> buscar(Long id);
	ReciboDTO inserirRecibo(Recibo recibo);
	boolean idExisteRecibo(Long id);
	InputStream exportarPdf(ReciboTemplateDTO reciboTemplateDTO);
}
