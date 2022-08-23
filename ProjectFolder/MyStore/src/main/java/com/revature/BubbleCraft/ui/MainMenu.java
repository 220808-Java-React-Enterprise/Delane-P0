/*Program: Project 0 - Console store - BubbleCat
Programmer: Delane Green
Purpose:
Created: 08/10/2022
Last updated: 08/10/2022
* */
package com.revature.BubbleCraft.ui;


import com.revature.BubbleCraft.models.Customer;
import com.revature.BubbleCraft.utils.Navigation;

import java.util.Scanner;

public class MainMenu extends Navigation implements IMenu {
    private Customer customer;

    //Constructor
    public MainMenu(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void start() {

        Scanner input = new Scanner(System.in);

        do {
            System.out.println("\n\n\n\t\t  MAIN MENU");
            System.out.println("Welcome to " + shop.getName() + " " + user.getName() + "!");
            System.out.println("Enter a number to select from the options below.\n");
            System.out.println("[1] Shop\t\t[3] View Order History\n" +
                    "[2] Account\t\t[4] View Cart\n" +
                    "\n\t\t[X] Logout\n");
            String menuChoice = input.next();

            switch (menuChoice) {
                case "1":
                    new ShoppingMenu().start();
                    break;
                case "2":
                    new AccountMenu(customer, userService).start();
                    break;
                case "3":
                    new OrderMenu().ViewCustomerOrders(customer.getId());
                    break;
                case "4":
                    new CartMenu(customer).start();
                    break;
                case "x":
                case "X":
                    return;
                default:
                    System.out.println("\nSorry that's not an available option.\nPlease enter one from the menu.\n(Press enter to continue)\n");
                    input.nextLine();

            }
        } while (true);


    }


}
