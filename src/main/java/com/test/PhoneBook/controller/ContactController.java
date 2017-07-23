package com.test.PhoneBook.controller;

import com.test.PhoneBook.model.Contact;
import com.test.PhoneBook.service.ContactService;
import com.test.PhoneBook.exceptions.SuchContactsExistAllredyException;
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
//@SessionAttributes("contact")
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;
    private Contact contactToEdit;

    /*@ModelAttribute("contact")
    public Contact initContact(@RequestParam("contactId") Long id) {
        return this.contactService.findOne(id);
    }*/

    @GetMapping("/edit")
    public ModelAndView editContactView(@Valid @RequestParam(name = "contactId") Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("userContacts", contactService.findOne(id));
        mav.setViewName("contact-details-edit");
        mav.addObject("contact", new Contact());
        mav.addObject("contactId", id);
        logger.info("Id to edit editViewPoint getMethod " + id);
        contactToEdit = contactService.findOne(id);

        return mav;
    }

   /* @PostMapping("/edit")
    public ModelAndView editContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult result) throws SuchContactsExistAllredyException {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form");
            //mav.setViewName("contact-edit");
            //mav.addObject("userContacts", contactService.findOne(contact.getId()));
            //mav.addObject("contact", contact);
            //mav.setViewName("redirect:/app/contact/edit");
            ModelAndView modelAndView = new ModelAndView("redirect:/app/secure/contact-details");
            return modelAndView;
        }

        //contact.setId(Long.parseLong(id));
        contactService.addContact(contact);

        logger.info("Contact created successfully");

        ModelAndView modelAndView = new ModelAndView("redirect:/app/secure/contact-details");

        return modelAndView;
    }*/

    /*@GetMapping("/edit")
    public String editContactView(@Valid @RequestParam(name = "contactId") String id) {
        return "redirect:/app/secure/contact-details-edit";
    }*/

    @PostMapping(value = "/edit")
    public ModelAndView editContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult result,
                              @RequestParam(name = "contactId") String id)
            throws SuchContactsExistAllredyException {
        //logger.info("contact Id to edit " + id);
        //TODO add contact id
        //contactService.deleteContact();
        //contactService.deleteContact(id);

        //logger.info("Contact to edit id " + id);
        //Contact editableContact = contactService.findOne(Long.parseLong(id));

        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form");
            //mav.addObject("userContacts", contactService.findOne(Long.parseLong(id)));
            mav.setViewName("contact-details-edit");
            mav.addObject("contact", contact);
            mav.addObject("contactId", id);
            return mav;
        }

        logger.info("Id to edit Post method " + id);
        contactService.addContact(contact);

        logger.info("Contact created edited");

        ModelAndView modelAndView = new ModelAndView("redirect:/app/secure/contact-details");

        return modelAndView;
    }

    @GetMapping(value = "/delete")
    public String deleteContact(@RequestParam(name = "contactId") Long id) {
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
    public ModelAndView createContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult result)
            throws SuchContactsExistAllredyException {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form");
            mav.setViewName("contact-creation");
            mav.addObject("contact", contact);
            return mav;
        }

        contactService.addContact(contact);

        logger.info("Contact created successfully");

        ModelAndView modelAndView = new ModelAndView("redirect:/app/secure/contact-details");

        return modelAndView;
    }

}
