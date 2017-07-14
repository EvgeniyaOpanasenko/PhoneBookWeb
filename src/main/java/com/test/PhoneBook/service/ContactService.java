package com.test.PhoneBook.service;

import com.test.PhoneBook.model.Contact;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface ContactService {

    @Secured({"ROLE_ADMIN"})
    List<Contact> getAllContacts();

    boolean addContact(Contact contact);

    boolean deleteContact(Contact contact);

    boolean editContact(Contact contact);
}
