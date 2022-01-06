package com.dobreagenty.payloads;

import org.json.JSONObject;

public class CustomerDetails {
    public String personName;
    public String surname;
    public String email;
    public String phoneNumber; // in case it starts with +48 etc.

    public CustomerDetails(String name, String surname, String email, String phoneNumber) {
        this.personName = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public CustomerDetails(JSONObject json) {
        personName = json.getString("name");
        surname = json.getString("surname");
        email = json.getString("email");
        phoneNumber = json.getString("phoneNumber");
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("name", personName);
        json.put("surname", surname);
        json.put("email", email);
        json.put("phoneNumber", phoneNumber);
        return json.toString();
    }
}