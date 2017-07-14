package ru.hashfactory.empty.controller;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hashfactory.empty.config.MailConfig;
import ru.hashfactory.empty.service.MailService;

//@RestController
@Controller
public class MainRestController {
    @Autowired
    MailService mailService;

    @RequestMapping(value = "/sendMail")
    public String sendMail(@RequestParam("name") String name, @RequestParam("mail") String mail, @RequestParam("tel") String tel){
//        ResponseEntity<String>
        new Thread(new Runnable() {
            @Override
            public void run() {
                    mailService.send(name, "info@hashfactory.ru","Заявка","Name "+name+" mail "+mail+"  tel "+tel);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder builder = new StringBuilder();

                builder.append("<table cellpadding=30 style='margin-top:20px; margin-left:30px;' border='0'>");
                builder.append("<tr><td align=center><img src='http://hashfactory.ru/open/formail1.png' /></td>");
                builder.append("<td align=center><a href='www.hashfactory.ru'><img src='http://hashfactory.ru/open/logo.png' alt='www.hashfactory.ru' /></a></td></tr>");
                builder.append("<tr><td align=center colspan='2'><br/><h2>Приветствуем!</h2>");
                builder.append("<h3>В ближайшее время мы с вами свяжемся!</h3><br/></td></tr>");
                builder.append("<tr><td></td><td align=right><p>Служба поддежки <a href='mailto:admin@hashactory.ru'>admin@hashactory.ru</a></p><br/></td></tr></table>");

                mailService.send(name, mail, "Мы с вами свяжемся!", builder.toString());

            }
        }).start();
        return "redirect:/";
    }

//    @RequestMapping(value = "/sendMail/{name}/{mail}")
//    public void sendMail(@PathVariable("name") String name, @PathVariable("mail") String mail){
////        ResponseEntity<String>
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    MailConfig.send(name, mail);
//                } catch (EmailException e) {
//                    e.printStackTrace();
//
//                }
//            }
//        }).start();
//
//
////      return new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
//
////        return new ResponseEntity<String>("Ok", HttpStatus.OK);
//    }
}
