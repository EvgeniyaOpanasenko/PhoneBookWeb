package com.test.PhoneBook.service;


import com.test.PhoneBook.dao.IUserInfoDAO;
import com.test.PhoneBook.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserInfoDAO userInfoDAO;

    @Override
    public List<Contact> getAllUserContacts() {
        return userInfoDAO.getAllUserContacts();
    }
}
