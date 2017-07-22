package com.test.PhoneBook.service;

import com.test.PhoneBook.dao.ContactRepository;
import com.test.PhoneBook.dao.UserRepository;
import com.test.PhoneBook.exceptions.SuchContactsExistAllredyException;
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

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactServiceImpl() {
    }

    //TODO move from here to a separated class Service

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Contact> getAllContacts() {

        return (List<Contact>) contactRepository.findAll();
    }

    @Override
    public Contact addContact(Contact contact) throws SuchContactsExistAllredyException {
        if (getAllContacts().stream()
                .filter(contact1 -> contact1.getFirstName()
                        .equals(contact.getFirstName())).findFirst().isPresent()) {
            throw new SuchContactsExistAllredyException("Pick other name");
        }
        contact.setUser(getCurrentlyLoggedInUser());
        return contactRepository.save(contact);
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

    public UserDto getCurrentlyLoggedInUser() {
        String name = getLoggedInUserName();
        return userRepository.findByUserName(name);
    }

    @Override
    public Contact findOne(long id) {
        return contactRepository.findOne(id);
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
