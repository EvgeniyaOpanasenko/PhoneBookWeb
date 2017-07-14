package com.test.PhoneBook.service;

import com.test.PhoneBook.dao.ContactRepository;
import com.test.PhoneBook.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts() {
        return (List<Contact>) contactRepository.findAll();
    }

    @Override
    public boolean addContact(Contact contact) {
        return false;
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
