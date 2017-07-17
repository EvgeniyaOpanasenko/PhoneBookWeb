package com.test.PhoneBook.controller;

import com.test.PhoneBook.model.Contact;
import com.test.PhoneBook.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("app/contact")
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String contactDelete(@RequestParam(name="contactId")Long id) {
        logger.info("contact Id to delete " + id);
        contactService.deleteContact(id);
        return "redirect:/app/secure/contact-details";
    }

    @GetMapping("/creation")
    public ModelAndView createUserView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("contact-creation");
        mav.addObject("contact", new Contact());
        return mav;
    }

    @PostMapping("/creation")
    public ModelAndView createUser(@Valid Contact contact, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form");
            mav.setViewName("contact-creation");
            mav.addObject("contact", contact);
            return mav;
        }

        Contact newContact = new Contact();
        newContact.setAuthor(contact.getAuthor());
        newContact.setAddress(contact.getAddress());
        newContact.setCellPhone(contact.getCellPhone());
        newContact.setPatronymic(contact.getPatronymic());
        newContact.setHomePhone(contact.getHomePhone());

        contactService.addContact(newContact);
        mav.setViewName("contacts");

        logger.info("Contact created successfully");
        return mav;
    }
}
