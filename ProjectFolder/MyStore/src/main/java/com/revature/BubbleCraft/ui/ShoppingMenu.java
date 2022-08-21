package com.revature.BubbleCraft.ui;

import com.revature.BubbleCraft.daos.OrderDAO;
import com.revature.BubbleCraft.daos.ProductDAO;
import com.revature.BubbleCraft.models.Customer;
import com.revature.BubbleCraft.models.Order;
import com.revature.BubbleCraft.models.Product;
import com.revature.BubbleCraft.services.OrderService;
import com.revature.BubbleCraft.services.ProductService;
import com.revature.BubbleCraft.utils.Navigation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class ShoppingMenu extends Navigation implements IMenu{

    private final ProductService productService = new ProductService( new ProductDAO() );
    private final OrderService orderService = new OrderService( new OrderDAO() );

    private Customer customer = (Customer) user;

    public ShoppingMenu() {}

    @Override
    public void start() {

        Scanner input = new Scanner(System.in);

        do {
            System.out.println("This is the Shopping Menu");
            System.out.println("Welcome to the shopping Panel " + user.getName() +
                            ".\nHere you can view and add products to your cart.");
            System.out.println("Enter a letter to view product details.\n" +
                            "Enter a number to add a product directly to your cart.\n" +
                            "Enter [0] to return to the main menu or [001] to checkout:");

            DisplayProducts();

            int menuChoice = Integer.parseInt(input.next());

            if( menuChoice == 0 ) { return; }
            else if (menuChoice == 001) {
                try {
                    Checkout(customer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            ProductSelection(productService.getProductList(), menuChoice);

        } while (true);


    }

    //
    public void ProductSelection(List<Product> productList, int menuChoice) {

        //TODO: Consider turning thr user into a customer at the start of the main menu,
        //TODO: find a better wau to deal with the menu choice.

        System.out.println("How many do you want to add?\nAmount:\t");
        Integer amount = Integer.parseInt(input.next());

        //Subtracting 1 because of the offset product display.
        if( menuChoice - 1 < productList.size() ) {
            customer.addToCart(productList.get(menuChoice - 1), amount);
        }
        else { System.out.println("Hey, that's bot an option!"); }

    }

    //
    public void DisplayProducts() {

        List<Product> productList = productService.getProductList();
        int i = 0;

        for(Product p: productList) {
            i++;    //counter for product list display.
            System.out.println( "\n\tNAME\t\t\t\t\t\tPRICE");
            System.out.println( "[" + i + "]\t" + p.getName() + "\t\t$" + p.getSellingPrice() );

        }

    }

    public void Checkout(Customer customer) throws IOException {
        System.out.println("Checkout\n" +
                "Your item list:\n" +
                "Name\t\tAmount\t\tPrice");

        customer.viewCart();

        System.out.println("\n[P] Place Order\t\t\t[R] Return");

        switch(input.next()) {
            case "P":
            case "p":
                orderService.placeOrder( CreateOrder(customer) );
                customer.clearCart();
                return;
            case "R":
            case "r":
                return;
            default:

        }

    }

    public Order CreateOrder(Customer customer) {

        Order order = new Order();
        order.setOpId((int) (Math.random() * 100));
        order.setUserId(customer.getId());
        order.setId((int) (Math.random() * 100));
        order.setProductList( customer.getCart() );
        order.setShopId(shop.getId());

        return order;
    }

}
