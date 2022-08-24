/*Program: Project 0 - Console store - BubbleCat
Programmer: Delane Green
Purpose:
Created: 08/10/2022
Last updated: 08/10/2022
* */

package com.revature.BubbleCraft.models;



import com.revature.BubbleCraft.daos.ShopDAO;
import com.revature.BubbleCraft.services.ShopService;
import com.revature.BubbleCraft.utils.Navigation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class Shop {
    //Constants
    private final ShopService shopService = new ShopService( new ShopDAO() );

    //DATA FIELDS
    private String uuid;
    private int id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String email;
    private UUID manager_id;
    private Map<Integer, Integer> inventory = new LinkedHashMap<>();

    //CONSTRUCTORS
    public Shop() {}

    public Shop(String name, String street, String city, String state, String zip, String country, String phone, String email, UUID manager_id) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.manager_id = manager_id;
    }

    //Shop constructor
    public Shop(int id, String name, String street, String city, String state, String zip, String country, String phone, String email, UUID manager_id) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.manager_id = manager_id;
    }


    //GETTERS and SETTERS   //TODO: organize the getters and setters.
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UUID getManager_id() {
        return manager_id;
    }

    public void setManager_id(UUID manager_id) {
        this.manager_id = manager_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<Integer, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Integer, Integer> inventory) {
        this.inventory = inventory;
    }


    //address
    public String getAddress() { return this.street + " " + this.city + ", " + this.state + " " + this.zip; }
    public void setAddress(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    //Inventory methods

    public void addToInventory( Integer int1, Integer int2 ) {
        this.inventory.put( int1, int2);

    }
    public void removeFromInventory(Integer product, Integer amount) {

        if( this.inventory.containsKey( product ) ) {
            if( this.inventory.get( product ) > amount ) {
                this.inventory.put( product, this.inventory.get( product ) - amount );

            } else if ( this.inventory.get( product ) <= amount ) {
                this.inventory.remove( product );
            } else { System.out.println("Another one got through the net...");}

        }


    }

    public void getInventoryFromDB() {
        this.inventory = shopService.getShopInventory( this.id );

    }

    public void saveInventoryToDB() {

        shopService.saveShopInventory(this.inventory, this.id);
    }

    public void viewInventory() {


        this.getInventoryFromDB();//TODO temp test REMOVE

        System.out.println( "\n" + this.name + "'s Inventory");
        //System.out.println("To view product details enter the corresponding number. ");
        System.out.println("To restock a product enter its number followed by a space and the word restock.\n" +
                "(Enter: [0 R] to Return)");
        System.out.println( "\tPRODUCT\t\t\tAMOUNT");

        Map<Product,Integer> viewProducts = Navigation.ConvertIntToProduct(this.inventory);

        int i = 0;
        for(Map.Entry<Product,Integer> product: viewProducts.entrySet()) {

            i++;

            System.out.println( "[" + i + "]\t" + product.getKey().getName() + "\t\t" + product.getValue() );


        }

    }
    //METHODS
    public void RestockInventory(int product, int amount) {

        this.inventory.put( product, this.inventory.get( product ) + amount);

        this.saveInventoryToDB(); //TODO: temp create confirmationmethod for admin and move to there.

    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", manager='" + manager_id + '\'' +
                '}';
    }
}
