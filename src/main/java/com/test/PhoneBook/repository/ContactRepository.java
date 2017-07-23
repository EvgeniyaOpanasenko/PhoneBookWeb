package com.test.PhoneBook.repository;

import com.test.PhoneBook.model.Contact;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {
    List<Contact> findByLastName(@Param("lastName") String lastName);
    List<Contact> findByFirstName(@Param("firstName") String firstName);
    List<Contact> findByPatronymic(@Param("patronymic") String patronymic);
}
