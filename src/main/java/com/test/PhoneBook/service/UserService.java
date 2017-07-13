package com.test.PhoneBook.service;

import com.test.PhoneBook.model.Contact;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface UserService {
	 @Secured({"ROLE_ADMIN"})
     List<Contact> getAllUserArticles();
}
