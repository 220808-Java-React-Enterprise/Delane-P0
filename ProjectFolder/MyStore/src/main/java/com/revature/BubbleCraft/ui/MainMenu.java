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
    private Customer customer = (Customer) user;

    //Constructor
    public MainMenu() {}

    @Override
    public void start() {

        Scanner input = new Scanner(System.in);

        do {
            System.out.println("\t\tMAIN MENU");
            System.out.println("Welcome to " + shop.getName() + " " + user.getName() + "!");
            System.out.println("Enter a number to select from the options below;\n");
            System.out.println("[1] Account\t\t[4] View Orders\n" +
                    "[2] Shop\t\t[5] View Order History\n" +
                    "[3] View Cart\t[6] Shops near me\n" +
                    "[X] Logout\n");
            String menuChoice = input.next();

            switch (menuChoice) {
                case "1":
                    new AccountMenu().start();
                    break;
                case "2":
                    new ShoppingMenu().start();
                    break;
                case "3":
                    new CartMenu(customer).start();
                    break;
                case "4":
                    new OrderMenu().ViewCustomerOrders(customer.getId());
                    break;
                case "5":
                    new OrderMenu().start();
                    break;
                case "6":
                    new ShoppingMenu().start();
                    break;
                case "x":
                case "X":
                    return;
                default:
                    System.out.println("Sorry that's not an available option.\nPlease enter one from the menu.");

            }
        } while (true);


    }


}
