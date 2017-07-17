package com.test.PhoneBook.controller;

import com.test.PhoneBook.service.ContactService;
import com.test.PhoneBook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("app")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @GetMapping("secure/contact-details")
    public ModelAndView getAllUserContacts() {
        ModelAndView mav = new ModelAndView();

        mav.addObject("userContacts",
                contactService.getAllContactsByCurrentlyLoggedInUser());
        mav.setViewName("contacts");
        logger.info("All contacts collected for USER");

        return mav;
    }

    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage = "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        logger.info("Error");
        return mav;
    }

} 