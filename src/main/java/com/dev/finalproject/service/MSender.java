package com.dev.finalproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MSender {
    private static final String MAIL_FROM = "finalspringbootproject@gmail.com";
    @Autowired
    private JavaMailSender javaMailSender;


    public void send(String mailTo, String subject, String text) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(mailTo);
        mail.setFrom(MAIL_FROM);
        mail.setSubject(subject);
        mail.setText(text);
        this.javaMailSender.send(mail);

    }
}
