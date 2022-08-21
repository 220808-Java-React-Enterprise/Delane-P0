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
        ShoppingMenu:
        {
            do {
                System.out.println("This is the Shopping Menu");
                System.out.println("Welcome to the shopping Panel " + user.getName() +
                        ".\nHere you can view and add products to your cart.");
                System.out.println("Enter a letter to view product details.\n" +
                        "Enter a number to add a product directly to your cart.\n" +
                        "Enter [0] to return to the main menu or [C] to checkout:");

                DisplayProducts();

                String menuChoice = input.nextLine();

                if (menuChoice.charAt(0) == '0') {

                    break ShoppingMenu;

                } else if (Character.isDigit(menuChoice.charAt(0))) {

                    ProductSelection( productService.getProductList(), Integer.parseInt(menuChoice) );

                } else if (menuChoice.charAt(0) == 'C' || menuChoice.charAt(0) == 'c') {

                    try {

                        Checkout(customer);

                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }

            } while (true);
        }

        return;

    }

    //
    public void ProductSelection(List<Product> productList, int menuChoice) {

        //TODO: Consider turning thr user into a customer at the start of the main menu,
        //TODO: find a better wau to deal with the menu choice.


        //Subtracting 49 to make up for the offset of turning a char into an int..
        if( menuChoice - 49 < productList.size() ) {

            System.out.println("How many do you want to add?\nAmount:\t");
            Integer amount = Integer.parseInt(input.next());

            customer.addToCart(productList.get(menuChoice - 49), amount);
            System.out.println( amount + " " + productList.get(menuChoice - 49).getName() + " added to cart!\n");
        }
        else { System.out.println("Hey, that's bot an option!\n"); }

    }

    //
    public void DisplayProducts() {

        List<Product> productList = productService.getProductList();
        int i = 0;
        System.out.println( "\n\tNAME\t\t\tPRICE"); //Header

        for(Product p: productList) {
            i++;    //counter for product list display.
            System.out.println( "[" + i + "]\t" + p.getName() + "\t\t$" + p.getSellingPrice() );

        }

    }

    public void Checkout(Customer customer) throws IOException {

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
