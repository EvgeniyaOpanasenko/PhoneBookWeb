package com.test.PhoneBook.controller;

import com.test.PhoneBook.service.ContactService;
import com.test.PhoneBook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("secure/contact-details")
    public ModelAndView getAllUserContacts() {
        ModelAndView mav = new ModelAndView();

        String role = userService.getLoggedInUserRole();
        String name = userService.getLoggedInUserName();

        if (role.equals("ROLE_ADMIN")) {
            mav.addObject("userContacts", contactService.getAllContacts());
            mav.setViewName("contacts");
            logger.info("All contacts collected by author ADMIN");
        } else {
            mav.addObject("userContacts", contactService.getAllContactsByAuthor(name));
            mav.setViewName("contacts");
            logger.info("All contacts collected for USER");
        }

       /* mav.addObject("userContacts", contactService.getAllContacts());
        mav.setViewName("contacts");*/
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