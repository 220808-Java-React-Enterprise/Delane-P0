package com.revature.BubbleCraft.ui;

import java.util.Scanner;

public class ShoppingMenu implements IMenu{

    public ShoppingMenu() {}

    @Override
    public void start() {

        Scanner input = new Scanner(System.in);

        do {
            System.out.println("This is the Shopping Menu");
            System.out.println("Welcome to the shopping Panel" + "[Username]" +
                            ".\nHere you can view and add products to your cart.");
            System.out.println("Enter a letter to view product details.\n" +
                            "Enter a number to add a product directly to your cart.\n" +
                            "Enter multiple letters separated by spaces to view multiple details at once.\n" +
                            "");
            System.out.println("[1] Account\t\t[4] View Orders\n" +
                    "[2] Shop\t\t[5] View Order History\n" +
                    "[3] View Cart\t\t[6] Shops near me\n" +
                    "[Q] Quit\n");
            String menuChoice = input.next();

            switch (menuChoice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "q":
                case "Q":
                    break;
                default:

            }
        } while (true);


    }
}
