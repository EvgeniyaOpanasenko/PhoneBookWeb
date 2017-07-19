package com.test.PhoneBook.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.PhoneBook.dao.ContactRepository;
import com.test.PhoneBook.model.Contact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactServiceImplTest {

    //@Autowired
    //ContactRepository contactRepository;

    /*private ContactService createContactService;
    private ContactRepository contactRepositoryMock;


    @Before
    public void setUp() throws Exception {
        contactRepositoryMock = Mockito.mock(ContactRepository.class);
        createContactService = new ContactServiceImpl(contactRepositoryMock) ;

    }*/

    //TODO create mock array of users and contacts
    //@Autowired
    //MockMvc mockMvc;

    @MockBean
    ContactService contactService;

    @Autowired
    ObjectMapper objectMapper;

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllContacts() throws Exception {
        //createContactService.getAllContacts();
        //assertArrayEquals((new ArrayList<Contact>), createContactService.getAllContacts());
    }

    @Test
    public void addContact() throws Exception {
        given(contactService.addContact(new Contact("Foo"))).willReturn(new Contact("Foo"));
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