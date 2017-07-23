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
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @GetMapping("/edit")
    public ModelAndView editContactView(@Valid @RequestParam(name = "contactId") Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("userContacts", contactService.findOne(id));
        mav.setViewName("contact-details-edit");
        Contact contact = new Contact();
        contact.setId(id);
        mav.addObject("contact", contact);
        mav.addObject("contactId", id);
        logger.info("Id to edit editViewPoint getMethod " + id);

        return mav;
    }


    @PostMapping(value = "/edit")
    public ModelAndView editContact(@RequestParam(name = "contactId") Long id, @Valid @ModelAttribute("contact") Contact contact, BindingResult result)
            throws SuchContactsExistAllredyException {

        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form");
            mav.setViewName("contact-details-edit");
            mav.addObject("contact", contact);
            return mav;
        }

        logger.info("Id to edit Post method " + id);

       /* Contact editableContact = contactService.findOne(id);
        Contact update = contactService.compareContatcs(editableContact, contact);*/

        if (contactService.addContact(contact)) {
            contactService.deleteContact(id);
            //contact.setId(id);
        }

        logger.info("Contact edited ID " + id);

        ModelAndView modelAndView =
                new ModelAndView("redirect:/app/secure/contact-details");

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
