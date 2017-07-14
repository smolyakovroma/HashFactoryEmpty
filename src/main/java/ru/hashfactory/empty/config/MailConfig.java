package ru.hashfactory.empty.config;

import org.apache.commons.mail.*;

import javax.mail.Authenticator;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class MailConfig {

    private MailConfig() {

    }

    public static void send(String name, String mail, String subject, String body) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.yandex.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("hashfactory@yandex.ru", "1234Zz"));
        email.setSSLOnConnect(true);
        email.setFrom("hashfactory@yandex.ru","HashFactory.ru");
        email.setSubject(subject);
        email.setHtmlMsg(body);

        email.setCharset(StandardCharsets.UTF_8.name());
        email.addTo(mail);


        email.send();
    }
}
