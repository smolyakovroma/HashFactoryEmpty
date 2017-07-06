package ru.hashfactory.empty.controller;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.hashfactory.empty.config.MailConfig;
import ru.hashfactory.empty.domain.User;
import ru.hashfactory.empty.service.UserService;

@Controller
public class MainController {

    //TODO обнвление курс доллара
    //TODO учет сложности для каждой монеты
    //TODO удлание строк в сравнение
    //TODO сохранение в ексель пдф
    //TODO срок окупаемости в долях дней

    @Autowired
    UserService userService;

    @RequestMapping(value = "/")
    public String main() {
        return "index";
    }

    @RequestMapping(value = "/2/index")
    public String index2() {
        return "index2";
    }


    @RequestMapping(value = "/beta/calculator")
    public String calc() {
        return "calculator";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(@RequestParam String email) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        modelAndView.addObject("email", email);
        return modelAndView;
    }

    //TODO Выдавать ошибку что не правильно ввели оба пароля при редеректе
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registrationCompleted(@RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam String password2) {
        ModelAndView modelAndView = new ModelAndView();
        if(password.equals(password2) & !name.trim().isEmpty()){
            User user = userService.findUserByEmail(email.trim());
            user.setName(name);
            user.setPassword(password.trim());
            user.setActive(1);
            userService.saveUser(user);
            modelAndView.setViewName("login");
            return modelAndView;
        }

        if(!password.equals(password2)) {
            modelAndView.addObject("error", "Пароли должны совпадать");
        }else if(name.trim().isEmpty()){
            modelAndView.addObject("error", "Заполните имя");
    }
        //TODO в продакщене заменить
//        return "redirect:/www.hashfactory/registration?email="+email.trim();
        modelAndView.addObject("name",name.trim());
        modelAndView.addObject("email",email.trim());
        modelAndView.setViewName("registration");

        return modelAndView;
    }
//    @RequestMapping(value = "/contact", method = RequestMethod.POST)
//    public void contact(@RequestParam("name") String name, @RequestParam("mail") String mail){
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        MailConfig.send(name, mail);
//                    } catch (EmailException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();

//        return "redirect:/";
//    }
}
