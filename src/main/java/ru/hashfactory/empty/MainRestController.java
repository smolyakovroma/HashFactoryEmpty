package ru.hashfactory.empty;

import org.apache.commons.mail.EmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class MainRestController {
    @RequestMapping(value = "/sendMail")
    public void sendMail(@RequestParam("name") String name, @RequestParam("mail") String mail){
//        ResponseEntity<String>
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
