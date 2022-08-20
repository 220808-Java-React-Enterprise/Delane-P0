/*Program: Project 0 - Console store - BubbleCat
Programmer: Delane Green
Purpose:
Created: 08/10/2022
Last updated: 08/10/2022
* */

package com.revature.BubbleCraft.models;

import java.util.Map;

public class Customer extends User{

    //Data fields
    private Map<Product, Integer> cart;

    //Constructors
    public Customer() {}

    public Customer(String name) {
        super( name );
    }
    public Customer(String name, String email, String password) {
        super( name, email, password );

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
    public void addToCart(Product product, Integer amount) {

        if( this.cart.containsKey( product ) ) {

            this.cart.replace( product, ( this.cart.get( product ) + amount ) );

        }
        else { this.cart.put( product, amount ); }

    }

    //REMOVE FROM CART
    public void removeFromCart(Product product, Integer amount) {

        if( this.cart.get( product ) > amount ) {

            this.cart.replace( product, ( this.cart.get( product ) - amount ) );

        }
        else { this.cart.remove( product ); }

    }

    //CLEAR CART
    public void clearCart() {

        this.cart.clear();

    }






}
