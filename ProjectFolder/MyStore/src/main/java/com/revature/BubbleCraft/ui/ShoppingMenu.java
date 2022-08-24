package com.revature.BubbleCraft.ui;

import com.revature.BubbleCraft.daos.OrderDAO;
import com.revature.BubbleCraft.daos.ProductDAO;
import com.revature.BubbleCraft.daos.ShopDAO;
import com.revature.BubbleCraft.models.Customer;
import com.revature.BubbleCraft.models.Order;
import com.revature.BubbleCraft.models.Product;
import com.revature.BubbleCraft.services.OrderService;
import com.revature.BubbleCraft.services.ProductService;
import com.revature.BubbleCraft.services.ShopService;
import com.revature.BubbleCraft.utils.Navigation;

import java.io.IOException;
import java.util.*;

public class ShoppingMenu extends Navigation implements IMenu {

    private final ProductService productService = new ProductService(new ProductDAO());
    private final OrderService orderService = new OrderService(new OrderDAO());
    private final ShopService shopService = new ShopService((new ShopDAO()));

    private Customer customer = (Customer) user;

    public ShoppingMenu() {
    }

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

                productService.DisplayProducts();

                System.out.println("\n[R] Return\t\t[C] View Cart & Checkout!");

                String menuChoice = input.nextLine();
                try {

                    if (menuChoice.matches("^[r\\|R]")) {

                        break ShoppingMenu;

                    } else if (Character.isDigit(menuChoice.charAt(0))) {

                        productService.ProductSelection(customer, Integer.parseInt(menuChoice), input, shop);

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


    public void Checkout(Customer customer) throws IOException {

        customer.viewCart();

        System.out.println("\n[P] Place Order\t\t\t[R] Return");

        switch (input.next()) {
            case "P":
            case "p":
                if (customer.getAddress().equals(null)) {
                    System.out.println("Hey! you need to add an address before you can place an order.\n" +
                            "Add one now? (The contents of your cart will be saved.)\n" +
                            "\t\t\t[1] Yes\t[2] No");
                    String choice = input.nextLine();
                    if (input.nextLine().matches("^[y\\|Y]")) {
                        new AccountMenu(customer, Navigation.userService).ChangeAddress();
                    } else {
                        System.out.println("Add an address from the account menu to finish placing your order.");
                        return;

                    }

                }
                if (!customer.getCart().isEmpty()) {
                    orderService.placeOrder(orderService.CreateOrder(customer, shop));
                    shopService.RemoveSoldStock(customer, shop);
                    customer.clearCart();

                    shop.saveInventoryToDB();   //TODO: temp remove after finding a better place.
                    System.out.println("Order placed!\n");
                } else {
                    System.out.println("Your cart is empty! You can place an order after adding products.");

                }
                return;
            case "R":
            case "r":
                return;
            default:

        }

    }
}


