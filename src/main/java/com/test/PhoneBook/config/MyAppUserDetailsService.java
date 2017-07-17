package com.test.PhoneBook.config;

import com.test.PhoneBook.dao.UserRepository;
import com.test.PhoneBook.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MyAppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDto activeUserDto = userRepository.findByUserName(userName);
        GrantedAuthority authority = new SimpleGrantedAuthority(activeUserDto.getRole());
        UserDetails userDetails = (UserDetails) new User(activeUserDto.getUserName(),
                activeUserDto.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}

