/*Program: Project 0 - Console store - BubbleCat
Programmer: Delane Green
Purpose:
Created: 08/10/2022
Last updated: 08/10/2022
* */

package com.revature.BubbleCraft.models;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Customer extends User {

    //Data fields
    private Map<Product, Integer> cart = new LinkedHashMap<>();

    //Constructors
    public Customer() {
    }

    public Customer(String name) {
        super(name);
    }

    public Customer(String name, String email, String password) {
        super(name, email, password);

    }

    //Full constructor for reading from DB
    public Customer(UUID id, String name, String password, String email, String street, String city, String state, String zip, String country, String phone, String role, LocalDate registered, LocalDate lastlogin) {
        super(id, name, password, email, street, city, state, zip, country, phone, role, registered, lastlogin);
    }


    //GETTERS & SETTERS
    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Product, Integer> cart) {
        this.cart = cart;
    }


    //Methods
    //ADD TO CART
    public void addToCart(Product product, Integer amount) { //TODO


        for (Map.Entry<Product, Integer> cartMap : cart.entrySet()) {

            if (cartMap.getKey().getId().equals(product.getId())) {

                //If the product is found in the cart add to its count and exit before adding another entry.
                cart.put(cartMap.getKey(), cart.get(cartMap.getKey()) + amount);
                return;
            }
        }
        cart.put(product, amount);
    }

    //REMOVE FROM CART
    public void removeFromCart(Product product, Integer amount) {

        if (this.cart.get(product) > amount) {

            this.cart.replace(product, (this.cart.get(product) - amount));

        } else {
            this.cart.remove(product);
        }

    }

    //CLEAR CART
    public void clearCart() {

        this.cart.clear();

    }

    //GET CART TOTAL
    public double GetCartTotal() {

        double cartTotal = 0;

        for (Map.Entry<Product, Integer> product : cart.entrySet()) {
            double cost = product.getKey().getSellingPrice() * product.getValue();

            cartTotal += cost;
        }

        return cartTotal;
    }

    //VIEW CART
    public void viewCart() {
        double cartTotal = 0;
        System.out.println("\t\t\t\tCART\n" +
                "\tName\t\t\tAmount\t\tPrice");

        int i = 0;
        for (Map.Entry<Product, Integer> product : cart.entrySet()) {
            i++;
            double cost = product.getKey().getSellingPrice() * product.getValue();
            System.out.println("[" + i + "]" + product.getKey().getName() + "\t\t" +
                    product.getValue() + "\t\t\t$" + cost);
            cartTotal += cost;

        }

        System.out.println("\n\t\t\t\tTotal Price: $" + cartTotal);


    }

    public void viewOrders(List<Order> orderList) {

        int count = 0;
        for (Order order : orderList) {
            count++;
            System.out.println("[" + count + "] " + order.getShopId() + "\t" + order.getDatePlaced());
        }

    }




}///C:ASS End
