package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.ProductDAO;
import com.revature.BubbleCraft.models.Customer;
import com.revature.BubbleCraft.models.Product;
import com.revature.BubbleCraft.models.Shop;
import com.revature.BubbleCraft.utils.Navigation;

import java.util.List;
import java.util.Scanner;

public class ProductService {

    //Data fields
    private final ProductDAO productDAO;

    //Constructors
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;

    }

    public List<Product> getProductList() {

        return productDAO.getAll();

    }


    public void DisplayProducts() {

        List<Product> productList = getProductList();
        int i = 0;
        System.out.println( "\n\tNAME\t\t\tPRICE\t\tSIZE\t\tSCENT"); //Header

        for(Product p: productList) {
            i++;    //counter for product list display.
            System.out.println( "[" + i + "]\t" + p.getName() + "\t\t$" + p.getSellingPrice() + "\t\t" + p.getWeight() + "\t\t" + p.getScent()); //+ "\nDESCRIPTION: " + p.getDescription() + "\n");

        }

    }

    public void DisplayProductDetails(int id) {
        Product product = Navigation.ConvertIntToProduct(id);
        System.out.println("");

    }

    //
    public void ProductSelection(Customer customer, int menuChoice, Scanner input, Shop shop) {

        //TODO: find a better wau to deal with the menu choice.
        List<Product> productList = getProductList();

        //Subtracting 1 to make up for the offset of having the menu start at 1.
        int offSet = menuChoice - 1;

        if( offSet < productList.size() ) {

            System.out.println("How many do you want to add?\nAmount:\t");
            Integer amount = Integer.parseInt(input.next());
            //TODO:Impliment limited purchase amount by stock.
            /*if(amount > shop.getInventory().get(offSet)) {
                System.out.println("Sorry! that's more that we have in stock!");

            }*/

            customer.addToCart(productList.get(offSet), amount);
            System.out.println( amount + " " + productList.get(offSet).getName() + " added to your cart!\n");
            input.nextLine();
        }
        else { System.out.println("Hey, that's not for sale!\n"); }

        input.nextLine();

    }




}
