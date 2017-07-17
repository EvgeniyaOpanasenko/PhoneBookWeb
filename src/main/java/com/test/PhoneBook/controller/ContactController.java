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
    public String contactDelete(@RequestParam(name = "contactId") Long id) {
        logger.info("contact Id to delete " + id);
        contactService.deleteContact(id);
        return "redirect:/app/secure/contact-details";
    }

    @GetMapping("/creation")
    public ModelAndView createContactView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("contact-creation");
        mav.addObject("contact", new Contact());
        return mav;
    }

    @PostMapping("/creation")
    public String createContact(@Valid Contact contact, BindingResult result) {

        Contact newContact = new Contact();
        newContact.setHomePhone(contact.getHomePhone());
        newContact.setCellPhone(contact.getCellPhone());
        newContact.setAddress(contact.getAddress());
        newContact.setUser(contactService.getCurrentlyLoggedInUser());
        newContact.setFirstName(contact.getFirstName());
        newContact.setLastName(contact.getLastName());
        newContact.setPatronymic(contact.getPatronymic());
        newContact.setMail(contact.getMail());
        contactService.addContact(newContact);

        logger.info("Contact created successfully");

        return "redirect:/app/secure/contact-details";
    }
}
