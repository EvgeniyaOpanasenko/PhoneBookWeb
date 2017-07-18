package com.test.PhoneBook.service;

import com.test.PhoneBook.model.Contact;
import com.test.PhoneBook.model.UserDto;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface ContactService {

    @Secured({"ROLE_ADMIN"})
    List<Contact> getAllContacts();

    Contact addContact(Contact contact);

    boolean editContact(Contact contact);

    void deleteContact(Long id);

    List<Contact> getAllContactsByCurrentlyLoggedInUser();

    UserDto getCurrentlyLoggedInUser();

    Contact getContactToEdit(Long id);

}
