package com.revature.BubbleCraft.ui;

import com.revature.BubbleCraft.models.Customer;

public class CartMenu implements IMenu{

    private Customer customer;

    private String id;


    public CartMenu( Customer customer ) {
        this.customer = customer;

    }
    @Override
    public void start() {
        customer.viewCart();

        //Things to be moved over from shopping menu.
    }
}
