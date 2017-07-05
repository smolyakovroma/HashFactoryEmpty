package ru.hashfactory.empty.config;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javax.mail.Authenticator;

public class MailConfig {

    private MailConfig() {

    }

    public static void send(String name, String mail, String subject, String body) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.yandex.ru");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("hashfactory@yandex.ru", "1234Zz"));
        email.setSSLOnConnect(true);
        email.setFrom("hashfactory@yandex.ru");
        email.setSubject(subject);
        email.setMsg(body);
        email.addTo(mail);
        email.send();
    }
}
