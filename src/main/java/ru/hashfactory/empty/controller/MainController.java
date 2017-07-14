package ru.hashfactory.empty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.hashfactory.empty.domain.User;
import ru.hashfactory.empty.service.UserService;

@Controller
@RequestMapping
@SessionAttributes("user")
public class MainController {

    //TODO обнвление курс доллара
    //TODO учет сложности для каждой монеты
    //TODO удлание строк в сравнение
    //TODO сохранение в ексель пдф
    //TODO срок окупаемости в долях дней

    @Autowired
    UserService userService;

    @ModelAttribute(name = "user")
    public User user() {
        return new User();
    }

    @RequestMapping(value = "/")
    public ModelAndView main(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("user", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

//    @RequestMapping(value = "/error")
//    public String error() {
//        return "error";
//    }

    @RequestMapping(value = "/2/index")
    public String index2() {
        return "index2";
    }


    @RequestMapping(value = "/information")
    public String information() {
        return "information";
    }


    @RequestMapping(value = "/aboutus")
    public String aboutus() {
        return "aboutus";
    }


    @RequestMapping(value = "/beta/calculator")
    public String calc() {
        return "calculator";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(@RequestParam String email) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserByEmail(email.trim());

        if (user == null || user.getActive() != 0) {
            modelAndView.setViewName("login");
            return modelAndView;
        }

        modelAndView.setViewName("registration");
        modelAndView.addObject("email", email);
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registrationCompleted(@RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam String password2) {
        ModelAndView modelAndView = new ModelAndView();
        if (password.equals(password2) & !name.trim().isEmpty()) {
            User user = userService.findUserByEmail(email.trim());
            user.setName(name);
            user.setPassword(password.trim());
            user.setActive(1);
            userService.saveNewUser(user);
            modelAndView.setViewName("login");
            return modelAndView;
        }

        if (!password.equals(password2)) {
            modelAndView.addObject("error", "Пароли должны совпадать");
        } else if (name.trim().isEmpty()) {
            modelAndView.addObject("error", "Заполните имя");
        }
        modelAndView.addObject("name", name.trim());
        modelAndView.addObject("email", email.trim());
        modelAndView.setViewName("registration");

        return modelAndView;
    }

}
