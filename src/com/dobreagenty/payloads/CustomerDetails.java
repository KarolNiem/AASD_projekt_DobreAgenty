package com.dobreagenty.payloads;

public class CustomerDetails {
    public String name;
    public String surname;
    public String email;
    public String phoneNumber; // in case it starts with +48 etc.

    public CustomerDetails(String name, String surname, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
