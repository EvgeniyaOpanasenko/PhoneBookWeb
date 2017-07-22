package com.test.PhoneBook.service;

import com.test.PhoneBook.exceptions.SuchContactsExistAllredyException;
import com.test.PhoneBook.model.Contact;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface ContactService {

    @Secured({"ROLE_ADMIN"})
    List<Contact> getAllContacts();

    Contact addContact(Contact contact) throws SuchContactsExistAllredyException;

    void deleteContact(Long id);

    List<Contact> getAllContactsByCurrentlyLoggedInUser();

    Contact findOne(long id);
}
