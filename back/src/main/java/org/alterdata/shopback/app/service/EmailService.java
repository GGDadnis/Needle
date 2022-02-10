package org.alterdata.shopback.app.service;

import org.alterdata.shopback.app.model.Email;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    Email enviarEmail(Email email, long id) throws MessagingException, IOException;
}
