package ru.hashfactory.empty.service;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;
import ru.hashfactory.empty.config.MailConfig;

@Service
public class MailServiceImpl implements MailService {
    @Override
    public void send(String name, String mail, String subject, String body) {
        try {
            MailConfig.send(name, mail, subject, body);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
