/*Program: Project 0 - Console store - BubbleCat
Programmer: Delane Green
Purpose:
Created: 08/10/2022
Last updated: 08/10/2022
* */
package com.revature.BubbleCraft.models;

public class User {
    //Data fields
    //Temp testing values!
    private String id = "" + Math.random() * 100;
    private String name;
    private String password;
    private String email;
    private String street = "uhu";
    private String city = "uhu";
    private String state = "uhu";
    private String zip = "uhu";
    private String country = "uhu";
    private String payment_method = "uhu";
    private String role = "po";


    //Constructors
    public User() {}
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //Methods


    //Overrides
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", eMail='" + email + '\'' +
                '}';
    }

    public String toString(int i) {
        return "User" +
                "name='" + name + '\'' +
                ", eMail='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public String toString(char c) {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", payment_method='" + payment_method + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
