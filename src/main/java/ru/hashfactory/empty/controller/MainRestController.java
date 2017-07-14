package ru.hashfactory.empty.controller;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hashfactory.empty.config.MailConfig;
import ru.hashfactory.empty.service.MailService;

@CrossOrigin
@RestController
public class MainRestController {
    @Autowired
    MailService mailService;

    @RequestMapping(value = "/sendMail")
    public void sendMail(@RequestParam("name") String name, @RequestParam("mail") String mail, @RequestParam("tel") String tel){
//        ResponseEntity<String>
        new Thread(new Runnable() {
            @Override
            public void run() {
                    mailService.send(name, "info@hashfactory.ru","Заявка","Name "+name+" mail "+mail+"  tel "+tel);
            }
        }).start();

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
