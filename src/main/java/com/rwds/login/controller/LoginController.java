package com.rwds.login.controller;

import javax.validation.Valid;

import com.rwds.login.domain.User;
import com.rwds.login.service.CustomUserDetailsService;

// import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    private CustomUserDetailsService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserBySurel(user.getSurel());
        if (userExists != null) {
            bindingResult.rejectValue("surel", "error.user", "Email Dan Nama Pengguna Sudah Terdaftar");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signup");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)

    public ModelAndView dashboard() {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserBySurel(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getNamalengkap());
        modelAndView.addObject("adminMessage", "Hanya Bisa Diakses Oleh Pengguna Terdaftar");
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }
}