package com.test.PhoneBook.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 5, max = 10)
   /* @Pattern(regexp = "\\`|\\~|\\!|\\@|\\#|\\$|\\%|\\^|\\&|\\*|" +
            "\\(|\\)|\\+|\\=|\\[|\\{|\\]|\\}|\\||\\\\|\\'|\\<|\\,|" +
            "\\.|\\>|\\?|\\/|\\\"\"|\\;|\\:|\\s")*/
    private String username;

    @Size(min = 5)
    private String password;

    @Size(min = 5)
    private String FIO;

    @OneToMany(mappedBy = "author")
    List<Contact> contactList;

    private short enabled;

    public User() {
    }

    public User(String username, String password,
                String FIO, List<Contact> contactList, short enabled) {
        this.username = username;
        this.password = password;
        this.FIO = FIO;
        this.contactList = contactList;
        this.enabled = enabled;
    }

    public User(String username, String password,
                String FIO) {
        this.username = username;
        this.password = password;
        this.FIO = FIO;
        this.contactList = contactList;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public short getEnabled() {
        return enabled;
    }

    public void setEnabled(short enabled) {
        this.enabled = enabled;
    }
}
