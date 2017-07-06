package ru.hashfactory.empty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.hashfactory.empty.domain.User;
import ru.hashfactory.empty.repository.UserRepository;
import ru.hashfactory.empty.service.MailService;
import ru.hashfactory.empty.service.UserService;

@Controller
@RequestMapping(value = "/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    MailService mailService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    @RequestMapping(value = "/newpassword/{id}", method = RequestMethod.GET)
    public String newPassword(@PathVariable int id) {
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.GET)
    public String newUser() {
        return "admin/newuser";
    }

    //TODO сделать нормальное тело письма
    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String newUserPost(@RequestParam String name, @RequestParam String email) {
        if (userService.findUserByEmail(email.trim()) == null) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(user.getEmail());
            user.setActive(0);
            userService.saveUser(user);
            StringBuilder builder = new StringBuilder();
            builder.append("<h2>Вы зарегистрированны на www.hashfactory.ru</h2><br/>")
                    .append("<p>для подтверждения регистрации пройдите по ссылки и  установите пароль к личному кабинету</p><br/>")
            .append("<a href='www.hashfactory.ru/registration?email="+email.trim()+"'>Перейти к регистрации</a><br/>")
            .append("<p>Служба поддежки <a href='mailto:admin@hashactory.ru'>admin@hashactory.ru</a></p><br/>");
            mailService.send(name, email, "Регистрация в личном кабинете www.hashfactory.ru", builder.toString());
        }
        return "redirect:/admin/users";
    }
}
