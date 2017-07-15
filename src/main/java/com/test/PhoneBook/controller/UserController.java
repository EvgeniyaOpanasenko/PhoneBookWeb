package com.test.PhoneBook.controller;


import com.test.PhoneBook.service.ContactService;
import com.test.PhoneBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("app")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("custom-login");
        return mav;
    }

    @GetMapping("secure/contact-details")
    public ModelAndView getAllUserContacts() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("userContacts", contactService.getAllContacts());
        mav.setViewName("contacts");
        return mav;
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }

    /*@PostMapping("signup")
    public ModelAndView signup(@ModelAttribute("userForm")) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("userContacts", contactService.getAllContacts());
        mav.setViewName("contacts");
        return mav;
    }*/
} 