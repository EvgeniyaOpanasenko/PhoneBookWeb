package com.test.PhoneBook.repository;

import com.test.PhoneBook.model.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        //TODO set up before refactor
    }


    @Test
    public void whenFindByUserName_thenReturnUser() throws Exception {
        //given
        UserDto inDataBase = new UserDto("kira");
        testEntityManager.persist(inDataBase);
        testEntityManager.flush();

        //when
        UserDto found = userRepository
                .findByUserName(inDataBase.getUserName());

        // then
        assertThat(found.getUserName())
                .isEqualTo(inDataBase.getUserName());


    }

}