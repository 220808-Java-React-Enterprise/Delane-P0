/*Program: Project 0 - Console store - BubbleCat
Programmer: Delane Green
Purpose:
Created: 08/10/2022
Last updated: 08/10/2022
* */
package com.revature.BubbleCraft.ui;


import java.util.Scanner;

public class MainMenu implements IMenu {

    //Constructor
    public MainMenu() {}

    @Override
    public void start() {

        Scanner input = new Scanner(System.in);

        do {
            System.out.println("This is the Main Menu");
            System.out.println("Welcome to " + "Bubble Craft" + "[Username]" + "!");
            System.out.println("Enter a number to select from the options below;\n");
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
