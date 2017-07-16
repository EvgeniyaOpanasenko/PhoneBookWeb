package com.test.PhoneBook.service;

import com.test.PhoneBook.controller.UserController;
import com.test.PhoneBook.dao.ContactRepository;
import com.test.PhoneBook.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts() {
        return (List<Contact>) contactRepository.findAll();
    }

    @Override
    public List<Contact> getAllContactsByAuthor(String author) {
        List<Contact> contacts = getAllContacts().stream()
                .filter(contact -> contact.getAuthor().getUserName()
                        .equals(author)).collect(Collectors.toList());
        contacts.forEach(System.out::println);
        return contacts;
    }

    @Override
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public boolean deleteContact(Contact contact) {
        return false;
    }

    @Override
    public boolean editContact(Contact contact) {
        return false;
    }
}
