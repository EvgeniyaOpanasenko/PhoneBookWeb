package com.test.PhoneBook.dao;

import com.test.PhoneBook.model.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDto, Long> {
    UserDto findByUserName(String userName);

}
