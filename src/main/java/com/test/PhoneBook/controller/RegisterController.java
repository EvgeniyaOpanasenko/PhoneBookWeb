package com.test.PhoneBook.controller;

import com.test.PhoneBook.model.UserInfo;
import com.test.PhoneBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("users")
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("register")
    public ModelAndView register(/*ModelAndView modelAndView,
                                 @Valid UserInfo user,
                                 @RequestParam("paramName") Long id, BindingResult result*/) {
        ModelAndView mav = new ModelAndView();
        //mav.addObject("userContacts", userService.save(newUser));
        mav.setViewName("register");
        return mav;
    }
}
