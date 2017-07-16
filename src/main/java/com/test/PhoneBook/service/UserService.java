package com.test.PhoneBook.service;

import com.test.PhoneBook.model.UserInfo;

import java.util.List;

public interface UserService {

    void create(UserInfo user);

    String getLoggedInUserRole();

    List<UserInfo> getAllUsers();

    String getLoggedInUserName();
}
