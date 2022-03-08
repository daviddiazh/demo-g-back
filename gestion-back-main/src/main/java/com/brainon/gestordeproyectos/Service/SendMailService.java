package com.brainon.gestordeproyectos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Validated
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void setMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private final String FROM = "app.preguntas.ddh@gmail.com"; //TODO: Agregar email.

    public SendMailService() {
    }

    public Mono<String> sendMail(String to, String subject, String body){
        try {

            MimeMessage message = javaMailSender.createMimeMessage();

            message.setSubject(subject);
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(FROM);
            helper.setTo(to);
            helper.setText(body, true);
            javaMailSender.send(message);
        } catch (MessagingException ex){
            System.out.println(ex.getMessage());

        }

        return Mono.just("¡Send!");
    }

}
