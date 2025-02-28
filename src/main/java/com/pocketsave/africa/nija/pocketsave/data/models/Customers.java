package com.pocketsave.africa.nija.pocketsave.data.models;

import jakarta.persistence.*;

import static jakarta.persistence.CascadeType.PERSIST;

@Entity
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    @OneToOne(mappedBy = "customer", cascade = PERSIST)
    private PocketWallet pocketWallet;

    public PocketWallet getPocketWallet() {
        return pocketWallet;
    }
    public void setPocketWallet(PocketWallet pocketWallet) {
        this.pocketWallet = pocketWallet;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
