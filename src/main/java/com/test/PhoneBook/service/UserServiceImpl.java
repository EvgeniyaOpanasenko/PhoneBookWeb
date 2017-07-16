package com.test.PhoneBook.service;

import com.test.PhoneBook.controller.UserController;
import com.test.PhoneBook.dao.UserRepository;
import com.test.PhoneBook.model.Contact;
import com.test.PhoneBook.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void create(UserInfo user) {
        userRepository.save(user);
    }

    @Override
    public String getLoggedInUserRole() {
        String name = getLoggedInUserName();
        //get logged in username
        logger.info("User logged in name" + name);
        String roleString = String.valueOf(getAllUsers().stream()
                .filter(userInfo -> userInfo.getUserName()
                        .equals(name)).findFirst());

        logger.info("User logged in role" + roleString);

        return roleString;
    }

    @Override
    public List<UserInfo> getAllUsers() {
        return (List<UserInfo>) userRepository.findAll();
    }

    @Override
    public String getLoggedInUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return name;
    }


}
