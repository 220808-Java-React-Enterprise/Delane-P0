package com.revature.BubbleCraft.models;

import java.time.LocalDate;
import java.util.UUID;

public class Admin extends User {

    //Data fields
    private int shopId;
    private Shop stationedStore;


    //Constructors
    public Admin() {
        super();
    }
    public Admin(String name, String email, String password) {
       super( name, email, password );
    }


    //Full constructor for reading from DB
    public Admin(UUID id, String name, String password, String email, String street, String city, String state, String zip, String country, String phone, String role, LocalDate registered, LocalDate lastlogin) {
        super( id, name, password, email, street, city, state, zip, country, phone, role, registered, lastlogin );
    }
}
