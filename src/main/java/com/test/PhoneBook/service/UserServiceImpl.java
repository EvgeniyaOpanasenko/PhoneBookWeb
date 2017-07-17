package com.test.PhoneBook.service;

import com.test.PhoneBook.dao.UserRepository;
import com.test.PhoneBook.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void create(UserDto user) {
        logger.info("User inserted " + user.getUserName());
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return (List<UserDto>) userRepository.findAll();
    }

}
