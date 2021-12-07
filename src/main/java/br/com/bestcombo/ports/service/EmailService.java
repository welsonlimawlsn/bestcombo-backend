package br.com.bestcombo.ports.service;

import br.com.bestcombo.adapters.email.dto.EmailTemplate;

public interface EmailService {

    void sendMail(EmailTemplate emailTemplate);

}
