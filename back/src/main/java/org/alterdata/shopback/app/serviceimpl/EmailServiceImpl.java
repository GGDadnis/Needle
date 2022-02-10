package org.alterdata.shopback.app.serviceimpl;

import org.alterdata.shopback.app.dto.ExibicaoCamposDTO;
import org.alterdata.shopback.app.dto.ReciboTemplateDTO;
import org.alterdata.shopback.app.model.Email;
import org.alterdata.shopback.app.model.Recibo;
import org.alterdata.shopback.app.repository.ReciboRepository;
import org.alterdata.shopback.app.service.EmailService;
import org.alterdata.shopback.app.service.ReciboService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.alterdata.shopback.app.model.enums.TipoLayoutReciboEnum.LAYOUT_1;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    ReciboRepository reciboRepository ;

    @Autowired
    ReciboService reciboService;

    @Override
    public Email enviarEmail(Email email, long id) throws MessagingException, IOException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setTo(email.getDestino());
        mimeMessageHelper.setFrom("backendshopalt@gmail.com");
        mimeMessageHelper.setText(email.getCorpo());
        mimeMessageHelper.setSubject(email.getAssunto());

        Recibo recibo = reciboRepository.getById(id);

        ReciboTemplateDTO reciboTemplateDTO= new ReciboTemplateDTO();
        ExibicaoCamposDTO todos = new ExibicaoCamposDTO(true,true,true,true);
        reciboTemplateDTO.setExibicaoCamposDTO(todos);
        reciboTemplateDTO.setServico(recibo.getServico());
        reciboTemplateDTO.setNomeMedico(recibo.getNomeMedico());
        reciboTemplateDTO.setNomePaciente(recibo.getNomePaciente());
        reciboTemplateDTO.setValor(recibo.getValor());
        reciboTemplateDTO.setTipoLayoutReciboEnum(LAYOUT_1);

        InputStream arquivoPdf = reciboService.exportarPdf(reciboTemplateDTO);
        File file = new File("recibos"+ reciboTemplateDTO.getNomePaciente() +"com_medico"+ reciboTemplateDTO.getNomeMedico()+".pdf" );
        FileUtils.copyInputStreamToFile(arquivoPdf,file);
        email.setReciboPDF(file);


        FileSystemResource fileSystemResource = new FileSystemResource(email.getReciboPDF());
        mimeMessageHelper.addAttachment("Recibo.pdf", fileSystemResource);
        javaMailSender.send(mimeMessage);

        System.out.print("enviando email...");
        return null;
    }
}
