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
        shop.getInventoryFromDB();  //still temp

        Scanner input = new Scanner(System.in);
        ShoppingMenu:
        {
            do {
                System.out.println("\n\t\tSHOPPING MENU");
                System.out.println("Welcome to the Shopping Menu " + user.getName() +
                        ". Here you can view and add products to your cart.");
                System.out.println("Enter a number to add a product directly to your cart.");
                       //+ "\n(Add a + to view product details!)");

                DisplayProducts();

                String menuChoice = input.nextLine();
                try {

                    if (menuChoice.charAt(0) == '0') {

                        break ShoppingMenu;

                    } else if (Character.isDigit(menuChoice.charAt(0))) {

                        ProductSelection(productService.getProductList(), Integer.parseInt(menuChoice));

                    } else if (menuChoice.charAt(0) == 'C' || menuChoice.charAt(0) == 'c') {

                        try {

                            Checkout(customer);

                        } catch (IOException e) {

                            e.printStackTrace();
                        }

                    } else {
                        continue;
                    }
                } catch (Exception e) {
                    continue;

                }

            } while (true);
        }

        return;

    }

    //
    public void ProductSelection(List<Product> productList, int menuChoice) {

        //TODO: Consider turning thr user into a customer at the start of the main menu,
        //TODO: find a better wau to deal with the menu choice.

        //Subtracting 1 to make up for the offset of having the menu start at 1.
        int offSet = menuChoice - 1;

        if( offSet < productList.size() ) {

            System.out.println("How many do you want to add?\nAmount:\t");
            Integer amount = Integer.parseInt(input.next());

            customer.addToCart(productList.get(offSet), amount);
            System.out.println( amount + " " + productList.get(offSet).getName() + "'s added to your cart!\n");
        }
        else { System.out.println("Hey, that's not for sale!\n"); }

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
                if(!customer.getCart().isEmpty()) {
                    orderService.placeOrder(CreateOrder(customer));
                    RemoveSoldStock(customer);
                    customer.clearCart();

                    shop.saveInventoryToDB();   //TODO: temp remove after finding a better place.
                    System.out.println("Order placed!\n");
                }
                else {
                    System.out.println("Your cart is empty! You can place an order after adding products.");

                }
                return;
            case "R":
            case "r":
                return;
            default:

        }

    }

    public void RemoveSoldStock(Customer customer) {

        for(Map.Entry<Product,Integer> cart: customer.getCart().entrySet()) {
            shop.removeFromInventory( cart.getKey().getId(), cart.getValue());
        }

    }

    public Order CreateOrder(Customer customer) {

        Order order = new Order();
        order.setUserId(customer.getId());
        order.setProductList( customer.getCart() );
        order.setShopId(shop.getId());
        order.setTotalCost(customer.GetCartTotal());

        return order;
    }

}
