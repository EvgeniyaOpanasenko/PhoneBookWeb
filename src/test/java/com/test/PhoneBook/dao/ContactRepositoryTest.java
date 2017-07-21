package com.test.PhoneBook.dao;

import com.test.PhoneBook.model.Contact;
import com.test.PhoneBook.model.UserDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
// to use SQL dataBase for testing purpose UNCOMENT AND COMMENT @AutoConfigureTestDatabase, @ActiveProfiles
//@AutoConfigureTestDatabase(replace = NONE)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    private Contact contact;

    @Before
    public void setUp() throws Exception {
        contactRepository.deleteAll();

        contact = new Contact(
                "vera", "ivanovna",
                "egorina", "(123) 456-7890",
                "(123) 456-7890", "kiev",
                "kira@mail.ru", new UserDto("author"));

        contactRepository.save(contact);

    }

    @After
    public void tearDown() throws Exception {
        contactRepository.deleteAll();
    }

    @Test
    public void findByLastName_then_return_ListContacts() throws Exception {

        List<Contact> actual = contactRepository.findByLastName(contact.getLastName());

        assertEquals("Found", contact.getLastName(), actual.get(0).getLastName());

    }

    @Test
    public void findByFirstName_then_return_ListContacts() throws Exception {

        List<Contact> actual = contactRepository.findByFirstName(contact.getFirstName());

        assertEquals("Found", contact.getFirstName(), actual.get(0).getFirstName());
    }

    @Test
    public void findByPatronymic_then_return_ListContacts() throws Exception {

        List<Contact> actual = contactRepository.findByPatronymic(contact.getPatronymic());

        assertEquals("Found", contact.getPatronymic(), actual.get(0).getPatronymic());
    }
}