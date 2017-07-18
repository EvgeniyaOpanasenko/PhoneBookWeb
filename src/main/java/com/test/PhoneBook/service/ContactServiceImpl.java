package com.test.PhoneBook.service;

import com.test.PhoneBook.dao.ContactRepository;
import com.test.PhoneBook.dao.UserRepository;
import com.test.PhoneBook.model.Contact;
import com.test.PhoneBook.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {
    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Contact> getAllContacts() {

        return (List<Contact>) contactRepository.findAll();
    }


    @Override
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public boolean editContact(Contact contact) {

        contactRepository.delete(contact.getId());
        contactRepository.save(contact);
        return true;
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.delete(id);
    }

    @Override
    public List<Contact> getAllContactsByCurrentlyLoggedInUser() {
        String name = getLoggedInUserName();
        return getAllContacts().stream()
                .filter(contact -> contact.getUser()
                        .getUserName().equals(name)).collect(Collectors.toList());

    }

    @Override
    public UserDto getCurrentlyLoggedInUser() {
        String name = getLoggedInUserName();
        return userRepository.findByUserName(name);

    }

    @Override
    public Contact getContactToEdit(Long id) {
        //TODO implement peek method
        return getAllContacts().stream()
                .filter(contact -> contact.getId() == id).findFirst().get();
    }


    private String getLoggedInUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //TODO need to implement org.springframework.security.core.userdetails.User
        // to get from Security Context Object of customUser
        String userName = auth.getName();
        logger.info("User logged in name " + userName);
        return userName;
    }
}
