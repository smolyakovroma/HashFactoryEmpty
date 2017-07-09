package ru.hashfactory.empty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.hashfactory.empty.domain.User;
import ru.hashfactory.empty.service.UserService;

@Controller
@RequestMapping("/cabinet")
@SessionAttributes("user")
public class DashboardController {

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByEmail(principal.getUsername());
        modelAndView.getModel().put("user", user);
        modelAndView.setViewName("cabinet/dashboard");
        return modelAndView;
    }

    @RequestMapping(value = "/ferms", method = RequestMethod.GET)
    public ModelAndView dashboardFerms(@SessionAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("user", user);
        modelAndView.setViewName("cabinet/ferms");
        return modelAndView;
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView settings(@SessionAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("user", user);
        modelAndView.setViewName("cabinet/settings");
        return modelAndView;
    }
}