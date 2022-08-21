/*Program: Project 0 - Console store - BubbleCat
Programmer: Delane Green
Purpose:
Created: 08/10/2022
Last updated: 08/10/2022
* */

package com.revature.BubbleCraft.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Order {
    //Data fields
    private int id;
    private int opId; //TODO: finish setting ordered products id.
    private LocalDate datePlaced;
    private boolean fulfilled;
    private LocalDate dateFulfilled;

    private UUID userId;
    private int productId;
    private Map<Product, Integer> productList;
    private int amount;
    private int shopId;

    //Constructors
    public Order() {}

    //ORDERS CONSTRUCTOR
    public Order(int id, LocalDate datePlaced, boolean fulfilled, LocalDate dateFulfilled) {
        this.id = id;
        this.datePlaced = datePlaced;
        this.fulfilled = fulfilled;
        this.dateFulfilled = dateFulfilled;
    }

    //ORDERED PRODUCTS CONSTRUCTOR
    public Order(int opid, UUID userId, int productId, int amount, int id) {
        this.opId = opid;
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
        this.id = id;
    }

    //Combined
    public Order(int id, int opId, LocalDate datePlaced, boolean fulfilled, LocalDate dateFulfilled, UUID userId, Map<Product, Integer> productList) {
        this.id = id;
        this.opId = opId;
        this.datePlaced = datePlaced;
        this.fulfilled = fulfilled;
        this.dateFulfilled = dateFulfilled;
        this.userId = userId;
        this.productList = productList;
    }


    //GETTERS & SETTERS


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(LocalDate datePlaced) {
        this.datePlaced = datePlaced;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    public LocalDate getDateFulfilled() {
        return dateFulfilled;
    }

    public void setDateFulfilled(LocalDate dateFulfilled) {
        this.dateFulfilled = dateFulfilled;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

    public Map<Product, Integer> getProductList() {
        return productList;
    }

    public void setProductList(Map<Product, Integer> productList) {
        this.productList = productList;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    //METHODS
    public void PlaceOrder(Order order) {


    }






    //toString Overload
    //TODO: change the output format of the toString statement.
    public String toString(int i) {

        switch( i ) {
            case 1: return "Order{" +
                    "id=" + id +
                    ", userId=" + userId +
                    ", productId=" + productId +
                    ", amount=" + amount +
                    '}';

            default: return "Order{" +
                     "id=" + id +
                     ", shop= " + shopId +
                    ", datePlaced=" + datePlaced +
                     ", fulfilled=" + fulfilled +
                     ", dateFulfilled=" + dateFulfilled +
                     '}';
        }

    }

}
