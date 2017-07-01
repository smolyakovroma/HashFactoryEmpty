package ru.hashfactory.empty;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    //TODO обнвление курс доллара
    //TODO учет сложности для каждой монеты
    //TODO удлание строк в сравнение
    //TODO сохранение в ексель пдф
    //TODO срок окупаемости в долях дней

    @RequestMapping(value = "/")
    public String main() {
        return "index";
    }

    @RequestMapping(value = "/beta/calculator")
    public String calc() {
        return "calculator";
    }


    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public void contact(@RequestParam("name") String name, @RequestParam("mail") String mail){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        MailConfig.send(name, mail);
                    } catch (EmailException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

//        return "redirect:/";
    }
}
