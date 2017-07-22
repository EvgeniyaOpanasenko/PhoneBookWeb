package com.test.PhoneBook.service;

import com.test.PhoneBook.dao.ContactRepository;
import com.test.PhoneBook.exceptions.SuchContactsExistAllredyException;
import com.test.PhoneBook.model.Contact;
import com.test.PhoneBook.model.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest {

    private Contact contact;

    @Mock
    private static ContactRepository contactRepository;

    @InjectMocks
    private static ContactService contactService = new ContactServiceImpl();

    @Test
    public void getAllContacts() throws Exception {
        Contact contact1 = new Contact(
                "vera", "ivanovna",
                "egorina", "(123) 456-7890",
                "(123) 456-7890", "kiev",
                "kira@mail.ru", new UserDto("author"));
        contactRepository.save(contact1);
        List<Contact> expected = new ArrayList<>();
        expected.add(contact1);
        when(contactRepository.findAll()).thenReturn(expected);

        List<Contact> retrivedContact = contactService.getAllContacts();
        assertEquals("Found", expected, retrivedContact);

    }

    @Test
    public void addContact() throws Exception, SuchContactsExistAllredyException {
        // Expected objects to save
        contact = new Contact(
                "vera", "ivanovna",
                "egorina", "(123) 456-7890",
                "(123) 456-7890", "kiev",
                "kira@mail.ru", new UserDto("author"));


        Contact expected = new Contact(
                "vera", "ivanovna",
                "egorina", "(123) 456-7890",
                "(123) 456-7890", "kiev",
                "kira@mail.ru", new UserDto("author"));

        // Mockito expectations
        when(contactRepository.save(contact)).thenReturn(expected);

        // Execute the method being tested
        Contact newContact = contactService.addContact(contact);

        //make sure method works
        verify(contactRepository).save(contact);
        //given(contactService.addContact(new Contact("Foo"))).willReturn(new Contact("Foo"));
        /*mockMvc.perform(post("/app/contact/creation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new CreateClientRequest("Foo"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Foo")))
                .andExpect(jsonPath("$.number", notNullValue()));*/
    }

    @Test
    public void editContact() throws Exception {

    }

    @Test
    public void deleteContact() throws Exception {
        // Expected objects to save
        contact = new Contact(
                "vera", "ivanovna",
                "egorina", "(123) 456-7890",
                "(123) 456-7890", "kiev",
                "kira@mail.ru", new UserDto("author"));


        Contact expected = new Contact(
                "vera", "ivanovna",
                "egorina", "(123) 456-7890",
                "(123) 456-7890", "kiev",
                "kira@mail.ru", new UserDto("author"));

        // Mockito expectations
        //when(contactRepository.delete(contact.getId())).thenReturn(expected);


        //when(contactService.deleteContact(contact.getId())).thenReturn(expected);
        // Execute the method being tested
        contactService.deleteContact(contact.getId());

        //make sure method works
        verify(contactRepository).delete(contact.getId());

    }

    @Test
    public void getAllContactsByCurrentlyLoggedInUser() throws Exception {
    }

    @Test
    public void getCurrentlyLoggedInUser() throws Exception {
    }

    @Test
    public void getContactToEdit() throws Exception {
    }

}