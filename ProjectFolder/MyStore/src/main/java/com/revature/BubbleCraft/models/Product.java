/*Program: Project 0 - Console store - BubbleCat
Programmer: Delane Green
Purpose:
Created: 08/10/2022
Last updated: 08/10/2022
* */

package com.revature.BubbleCraft.models;

import java.util.Currency;

public class Product {

    //Data fields
    private Integer id;
    private String name;
    private String type;
    private String color;
    private String scent;
    private String size;
    private String weight;
    private String brand;
    private String description;
    private double sellingPrice;
    private double costPrice;
    private String supplier;

    //Constructors
    public Product() {}

    public Product(Integer id, String name, String type, String color, String scent, String size, String weight, String brand, String description, double sellingPrice, double costPrice) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.color = color;
        this.scent = scent;
        this.size = size;
        this.weight = weight;
        this.brand = brand;
        this.description = description;
        this.sellingPrice = sellingPrice;
        this.costPrice = costPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getScent() {
        return scent;
    }

    public void setScent(String scent) {
        this.scent = scent;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", scent='" + scent + '\'' +
                ", weight='" + weight + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", costPrice=" + costPrice +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}
