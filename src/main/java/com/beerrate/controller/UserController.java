package com.beerrate.controller;

import com.beerrate.model.User;
import com.beerrate.service.SecurityService;
import com.beerrate.service.UserService;
import com.beerrate.validator.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("userForm", new User());

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null){
        	model.addAttribute("error", "Your username and password is invalid.");
        }

        return "login";
    }   
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(Model model, String error, String logout) {
        if (error != null){
            model.addAttribute("error", "Your username and password is invalid.");        	
        }

        return "login";
    }
    
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homepage(Model model, String error, String logout) {
    	//Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // http://www.mkyong.com/spring-security/get-current-logged-in-username-in-spring-security/
    	//String name = auth.getName(); //get logged in username
    	//model.addAttribute("username", name); 
    	
        if (logout != null){
            model.addAttribute("logoutMessage", "You have been logged out successfully.");        	
        }
    	
        return "index";
    }
}
