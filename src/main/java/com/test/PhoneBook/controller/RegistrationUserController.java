package com.test.PhoneBook.controller;

import com.test.PhoneBook.model.UserDto;
import com.test.PhoneBook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("app")
public class RegistrationUserController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationUserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("create-user")
    public ModelAndView createUserView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user-creation");
        mav.addObject("user", new UserDto());
        return mav;
    }

    @PostMapping("create-user")
    public ModelAndView createUser(@Valid @ModelAttribute("user") UserDto user, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form");
            mav.setViewName("user-creation");
            mav.addObject("user", user);
            return mav;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        UserDto registration = new UserDto();
        registration.setUserName(user.getUserName());
        registration.setPassword(hashedPassword);
        registration.setRole("ROLE_USER");
        userService.create(registration);
        mav.setViewName("login");
        //TODO logIn(user);
        logger.info("User created successfully");
        return mav;
    }

}
