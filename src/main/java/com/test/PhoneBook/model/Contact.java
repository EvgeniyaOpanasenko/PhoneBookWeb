package com.test.PhoneBook.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min = 4, max = 10)
    @NotNull
    private String firstName;

    //middle name
    @Size(min = 4, max = 10)
    @NotNull
    private String patronymic;

    @Size(min = 4, max = 10)
    @NotNull
    private String lastName;

    @NotNull
    @Pattern(regexp = "\\([0-9]{3}\\)\\s[0-9]{3}-[0-9]{2}-[0-9]{2}")
    private String cellPhone;

    private String homePhone;

    private String address;

    @Pattern(regexp = ".+@.+\\..+")
    private String mail;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserDto user;

    public Contact() {
    }

    public Contact(String firstName, String patronymic, String lastName) {
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.lastName = lastName;
    }

    public Contact(String firstName, String patronymic, String lastName, String cellPhone,
                   String homePhone, String address, String mail, UserDto user) {
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.lastName = lastName;
        this.cellPhone = cellPhone;
        this.homePhone = homePhone;
        this.address = address;
        this.mail = mail;
        this.user = user;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}