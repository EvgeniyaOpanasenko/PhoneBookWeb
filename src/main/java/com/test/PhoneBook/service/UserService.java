package com.test.PhoneBook.service;

import com.test.PhoneBook.model.UserDto;

import java.util.List;

public interface UserService {

    void create(UserDto user);

    List<UserDto> getAllUsers();

}
