package ru.hashfactory.empty.config;

import org.apache.commons.mail.*;

import javax.mail.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class MailConfig {

    private static URL url;
    private static URL url1;
    private static URL url2;
    private static URL url3;
    private static URL url4;
    private static URL url5;
    private static String cid;
    private static String cid1;
    private static String cid2;
    private static String cid3;
    private static String cid4;
    private static String cid5;
    static {


        try {
            url = new URL("http://hashfactory.ru/open/logo.png");
            url1 = new URL("http://hashfactory.ru/open/formail1.png");
            url2 = new URL("http://hashfactory.ru/open/formail2.png");
            url3 = new URL("http://hashfactory.ru/open/formail3.png");
            url4 = new URL("http://hashfactory.ru/open/formail4.png");
            url5 = new URL("http://hashfactory.ru/open/formail5.png");



        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    private MailConfig() {

    }

    public static void send(String name, String mail, String subject, String body) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.yandex.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("hashfactory@yandex.ru", "1234Zz"));
        email.setSSLOnConnect(true);
        email.setFrom("hashfactory@yandex.ru","HashFactory.ru");

        cid = email.embed(url, "logo");
        cid1 = email.embed(url1, "team1");
        cid2 = email.embed(url1, "team2");
        cid3 = email.embed(url1, "team3");
        cid4 = email.embed(url1, "team4");
        cid5 = email.embed(url1, "team5");

        body = body.replace("logo", "<img src=\"cid:"+cid+"\" alt='logo'></html>");
        body = body.replace("team1", "<img src=\"cid:"+cid1+"\" alt='team'></html>");
        body = body.replace("team2", "<img src=\"cid:"+cid2+"\" alt='team'></html>");
        body = body.replace("team3", "<img src=\"cid:"+cid3+"\" alt='team'></html>");
        body = body.replace("team4", "<img src=\"cid:"+cid4+"\" alt='team'></html>");
        body = body.replace("team5", "<img src=\"cid:"+cid5+"\" alt='team'></html>");
        // set the html message
        email.setSubject(subject);
        email.setHtmlMsg(body);

        email.setCharset(StandardCharsets.UTF_8.name());
        email.addTo(mail);


        email.send();
    }
}
