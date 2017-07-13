package com.test.PhoneBook.dao;


import com.test.PhoneBook.model.Contact;
import com.test.PhoneBook.model.UserInfo;

import java.util.List;

public interface IUserInfoDAO {
	UserInfo getActiveUser(String userName);
	List<Contact> getAllUserContacts();
}