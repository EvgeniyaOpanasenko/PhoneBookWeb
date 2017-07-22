package com.test.PhoneBook.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Pattern(regexp = "^[a-zA-Z0-9äöüÄÖÜ]*$")
    @Column(unique = true)
    private String userName;

    @Size(min = 5)
    private String password;

    private String role;

    @Size(min = 5)
    private String fullName;

    private short enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    List<Contact> contactList;

    public UserDto() {
    }

    public UserDto(String userName, String password, String role, short enabled) {
        this.userName = userName;
        this.password = password;
        this.role = "ROLE_USER";
        this.enabled = 1;
    }

    public UserDto(String userName, String password, String role,
                   String fullName, short enabled) {
        this.userName = userName;
        this.password = password;
        this.role = "ROLE_USER";
        this.enabled = 1;
        this.fullName = fullName;
    }

    public UserDto(String userName) {
        this.userName = userName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public short getEnabled() {
        return enabled;
    }

    public void setEnabled(short enabled) {
        this.enabled = enabled;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
