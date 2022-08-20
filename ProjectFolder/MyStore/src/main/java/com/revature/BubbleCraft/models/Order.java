/*Program: Project 0 - Console store - BubbleCat
Programmer: Delane Green
Purpose:
Created: 08/10/2022
Last updated: 08/10/2022
* */

package com.revature.BubbleCraft.models;

import java.time.LocalDate;
import java.util.Date;
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
    private int amount;

    //Constructors
    public Order() {}

    public Order(int id, LocalDate datePlaced, boolean fulfilled, LocalDate dateFulfilled) {
        this.id = id;
        this.datePlaced = datePlaced;
        this.fulfilled = fulfilled;
        this.dateFulfilled = dateFulfilled;
    }

    public Order(int id, UUID userId, int productId, int amount) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
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

    //METHODS






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
                     ", datePlaced=" + datePlaced +
                     ", fulfilled=" + fulfilled +
                     ", dateFulfilled=" + dateFulfilled +
                     '}';
        }

    }

}
