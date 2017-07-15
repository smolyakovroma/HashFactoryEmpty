package ru.hashfactory.empty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hashfactory.empty.service.MailService;

//@RestController
@Controller
public class MainRestController {
    @Autowired
    MailService mailService;

    @RequestMapping(value = "/sendMail")
    public String sendMail(@RequestParam("name") String name, @RequestParam("mail") String mail, @RequestParam("tel") String tel) {
//        ResponseEntity<String>

        mailService.send(name, "info@hashfactory.ru", "Заявка", "Name " + name + " mail " + mail + "  tel " + tel);


        StringBuilder builder = new StringBuilder();

        builder.append("<table cellpadding=15 style='margin-top:10px; margin-left:20px;' border='0'>");
        builder.append("<tr><td align=center  colspan='2'><a href='www.hashfactory.ru'>logo</a></td></tr>");
        builder.append("<tr><td align=center ><br/><h2>Приветствуем!</h2>");
        builder.append("<h3>В ближайшее время мы с вами свяжемся!</h3><br/></td>");
        builder.append("<td align=center>team2</td></tr>");
        builder.append("<tr><td></td><td align=right><p>Служба поддежки <a href='mailto:admin@hashactory.ru'>admin@hashactory.ru</a></p><br/></td></tr></table>");

        mailService.send(name, mail, "Мы с вами свяжемся!", builder.toString());


        return "redirect:/";
    }


}
