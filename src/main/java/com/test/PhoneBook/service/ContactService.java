package com.test.PhoneBook.service;


import com.test.PhoneBook.model.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> getAllContacts();
    boolean addContact(Contact contact);
    boolean deleteContact(Contact contact);
    boolean editContact(Contact contact);
}
