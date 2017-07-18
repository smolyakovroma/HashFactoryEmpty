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
import ru.hashfactory.empty.service.MailService;
import ru.hashfactory.empty.service.UserService;

@Controller
@RequestMapping(value = "/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;


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

    @RequestMapping(value = "/newpassword/{email}", method = RequestMethod.GET)
    public String newPassword(@PathVariable String email) {
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.GET)
    public String newUser() {
        return "admin/newuser";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editform(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();

        User user = userService.findUserById(id);
        if (user != null) {
            modelAndView.getModel().put("user", user);
            modelAndView.setViewName("admin/edit");
            return modelAndView;
        }
        modelAndView.setViewName("admin/users");
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestParam int id, @RequestParam String name, @RequestParam String email, @RequestParam int active, @RequestParam int typeClient) {
        User user = userService.findUserById(id);
        if (user != null) {
            user.setName(name.trim());
            user.setEmail(email.trim());
            user.setTypeClient(typeClient);
            user.setActive(active);
            userService.saveUser(user);
        }
        return "redirect:/admin/users";
    }

    //TODO сделать нормальное тело письма
    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String newUserPost(@RequestParam String name, @RequestParam String email, @RequestParam int typeClient) {
        if (userService.findUserByEmail(email.trim()) == null) {
            User user = new User();
            user.setName(name.trim());
            user.setEmail(email.trim());
            user.setTypeClient(typeClient);
            user.setPassword(user.getEmail().trim());
            user.setActive(0);
            userService.saveNewUser(user);
            StringBuilder builder = new StringBuilder();

            builder.append("<table cellpadding=15 style='margin-top:10px; margin-left:20px;' border='0'>");
            builder.append("<tr><td align=center  colspan='2'><a href='www.hashfactory.ru'>logo</a></td></tr>");
            builder.append("<tr><td align=center><br/><h2>Вы зарегистрированны на www.hashfactory.ru</h2>");
            builder.append("<h3>для подтверждения регистрации пройдите по ссылки и установите пароль к личному кабинету</h3><br/>");
            builder.append("<h3><a href='www.hashfactory.ru/registration?email=" + email.trim() + "'>Перейти</a></h3><br/><br/></td>");
            builder.append("<td align=center>team3</td></tr>");
            builder.append("<tr><td></td><td align=right><p>Служба поддежки <a href='mailto:admin@hashactory.ru'>admin@hashactory.ru</a></p>");
            builder.append("<p>Телефон 8 800 100 5979 (по России бесплатно)</p></td></tr></table>");

            mailService.send(name, email, "Регистрация в личном кабинете www.hashfactory.ru", builder.toString());
        }
        return "redirect:/admin/users";
    }
}
