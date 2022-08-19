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
    private String phone = "";
    //private String payment_method = ""; //Implement if time allows.
    private String role = "";


    //Constructors
    public User() {}
    //Guest constructor
    public User (String name) {
        this.name = name;
    }
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //Methods
    public void setAddress( String street, String city, String state, String zip, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;

    }
    public String getAddress() {
        return this.street + ", " + this.city + ", " + this.state + " " + this.zip + ", " + this.country;

    }


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
        return "User:" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'';
    }
}
