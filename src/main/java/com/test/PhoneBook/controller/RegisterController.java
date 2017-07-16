package com.test.PhoneBook.controller;

import com.test.PhoneBook.model.UserInfo;
import com.test.PhoneBook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /*@GetMapping("/registration")
    public ModelAndView createUserView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("registration");
        mav.addObject("user", new UserInfo());
        return mav;
    }
    @PostMapping("/registration")
    public ModelAndView createUser(@Valid UserInfo user, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if(result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            mav.setViewName("registration");
            mav.addObject("user", user);
            return mav;
        }
        userService.create(user);
        mav.setViewName("contacts");
        return mav;
        *//*userService.create(user);
        mav.addObject("allUsers", userService.getAllUserArticles());
        mav.setViewName("user-info");
        logger.info("Form submitted successfully.");
        return mav;*//*
    }*/


    //@ResponseBody
    /*@GetMapping("/registration")
    public String showRegistartionForm(Model model) {
        //ModelAndView mav = new ModelAndView();
        UserInfo user = new UserInfo();
        //user.setCountry("address");
       // user.setFullName("fullName");
       // user.setPassword("password");
        user.setUserName("userName");
        *//*mav.addObject("user", newUser);
        mav.setViewName("registration");*//*
        model.addAttribute("user", user);
        return "/registration";
    }*/

    /*@RequestMapping(value = "/", method = RequestMethod.POST)
    public String addNewPost(@Valid UserInfo post, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
       userService.saveNewUser(post);
        return "/registration";
    }*/

    @RequestMapping(method = RequestMethod.POST)
    public void processForm(@ModelAttribute(value = "user") UserInfo user,
                              BindingResult result) {
        userService.saveNewUser(user);
        //return "/contact-details";
        /*if (result.hasErrors()) {
            return "registration";
        } else {
            userService.create(user);
            System.out.println(user);
            System.out.println("User values is : " + user.getUserName());
            return "/app-login";
        }*/
    }

    /*@RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String processForm(@ModelAttribute(value = "user") UserInfo user) {
        ModelAndView model = new ModelAndView();
        model.getViewName();
        return null;
    }*/

   /* @RequestMapping(value = "/registration", method = RequestMethod.POST)
    String create(@ModelAttribute(value="user") UserInfo user) {
        userService.create(user);
        return "/app/secure/contact-details";
    }*/


   /* @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserInfo accountDto,
            BindingResult result,
            WebRequest request,
            Errors errors) {

        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        }
        else {
            return new ModelAndView("successRegister", "user", accountDto);
        }
    }

    private User createUserAccount(UserInfo accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = userService.saveNewUser(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }*/

   /* @RequestMapping(value = "/registration", method = RequestMethod.POST)
    String create(@RequestBody @Valid UserInfo createForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
            userService.create(createForm);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("username.exists", "Username already exists");
            return "user_create";
        }
        return "registration";
    }*/


   /* @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String processForm(@ModelAttribute(value = "user") UserInfo user) {
        return userService.create(user);
    }*/
}
