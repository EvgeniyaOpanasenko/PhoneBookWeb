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

    @GetMapping("/edit")
    public ModelAndView editContactView(@RequestParam(name = "contactId") Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("userContacts", contactService.getContactToEdit(id));
        mav.setViewName("contact-edit");
        mav.addObject("contact", new Contact());
        return mav;
    }

    @PostMapping("/edit")
    public String editContact(@Valid Contact contact, BindingResult result,
                              @RequestParam(name = "contactId") Long id) {
        logger.info("Contact to edit ID  " + contact.getId());
       /* Contact newContact = new Contact();
        newContact.setHomePhone(contact.getHomePhone());
        newContact.setCellPhone(contact.getCellPhone());
        newContact.setAddress(contact.getAddress());
        newContact.setUser(contactService.getCurrentlyLoggedInUser());
        newContact.setFirstName(contact.getFirstName());
        newContact.setLastName(contact.getLastName());
        newContact.setPatronymic(contact.getPatronymic());
        newContact.setMail(contact.getMail());
        //newContact.setId(contact.getId()+1);*/
        //contactService.deleteContact(id);
        contactService.addContact(contact);

        return "redirect:/app/secure/contact-details";
    }

    @GetMapping(value = "/delete")
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
    public ModelAndView createContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form");
            mav.setViewName("contact-creation");
            mav.addObject("contact", contact);
            return mav;
        }
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

        ModelAndView modelAndView = new ModelAndView("redirect:/app/secure/contact-details");

        return modelAndView;
    }

}
