package com.test.PhoneBook.service;

import com.test.PhoneBook.model.Contact;
import com.test.PhoneBook.model.UserInfo;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.List;

public interface UserService {
    List<Contact> getAllUserContacts();

    UserInfo saveNewUser(UserInfo accountDto);

    void create(UserInfo user);
}
