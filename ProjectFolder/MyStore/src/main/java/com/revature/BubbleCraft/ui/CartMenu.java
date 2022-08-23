package com.revature.BubbleCraft.ui;

import com.revature.BubbleCraft.models.Customer;

import java.io.IOException;

public class CartMenu implements IMenu{

    private Customer customer;

    private String id;


    public CartMenu( Customer customer ) {
        this.customer = customer;

    }
    @Override
    public void start() {
        try {
            new ShoppingMenu().Checkout(customer);
        } catch (IOException e) {
            customer.viewCart();
            throw new RuntimeException(e);
        }

        //Things to be moved over from shopping menu.
    }
}
