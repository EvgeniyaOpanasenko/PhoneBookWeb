package com.test.PhoneBook.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String surname;
    private String secondName;
    private String cellPhone;
    private String homePhone;
    private String address;
    private String mail;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User author;

    public Contact() {
    }

    public Contact(String name, String surname,
                   String secondName, String cellPhone,
                   String homePhone, String address, String mail, User author) {
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.cellPhone = cellPhone;
        this.homePhone = homePhone;
        this.address = address;
        this.mail = mail;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getAddress() {
        return address;
    }

    public String getMail() {
        return mail;
    }

    public User getAuthor() {
        return author;
    }
}
