package com.test.PhoneBook.controller;


import com.test.PhoneBook.model.UserInfo;
import com.test.PhoneBook.service.ContactService;
import com.test.PhoneBook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("app")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
        mav.setViewName("login");
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

    @GetMapping("create-user")
    public ModelAndView createUserView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user-creation");
        mav.addObject("user", new UserInfo());
        return mav;
    }

    @PostMapping("create-user")
    public ModelAndView createUser(@Valid UserInfo user, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            mav.setViewName("user-creation");
            mav.addObject("user", user);
            return mav;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        UserInfo registration = new UserInfo();
        registration.setUserName(user.getUserName());
        registration.setPassword(hashedPassword);
        registration.setRole("ROLE_USER");
        userService.create(registration);
        mav.setViewName("login");
        //logIn(user);
        logger.info("User created successfully.");
        return mav;
    }


} 