package com.test.PhoneBook.dao;


import com.test.PhoneBook.model.Contact;
import com.test.PhoneBook.model.UserInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserInfoDAO implements IUserInfoDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public UserInfo getActiveUser(String userName) {
        UserInfo activeUserInfo = new UserInfo();
        short enabled = 1;
        List<?> list = entityManager.createQuery("SELECT u FROM UserInfo u WHERE userName=? and enabled=?")
                .setParameter(1, userName).setParameter(2, enabled).getResultList();
        if (!list.isEmpty()) {
            activeUserInfo = (UserInfo) list.get(0);
        }
        return activeUserInfo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Contact> getAllUserContacts() {
        String hql = "FROM Contact as atcl ORDER BY atcl.id";
        return (List<Contact>) entityManager.createQuery(hql).getResultList();
    }
}