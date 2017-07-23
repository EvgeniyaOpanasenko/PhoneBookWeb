package com.test.PhoneBook.repository;

import com.test.PhoneBook.model.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDto, Long> {
    UserDto findByUserName(String userName);

}
