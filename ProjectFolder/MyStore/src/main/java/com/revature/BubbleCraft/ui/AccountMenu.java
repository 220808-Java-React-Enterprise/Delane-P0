package com.revature.BubbleCraft.ui;

import java.util.Scanner;

public class AccountMenu implements IMenu{

    public AccountMenu() {}

    @Override
    public void start() {

        Scanner input = new Scanner(System.in);

        do {
            System.out.println("This is the Account Menu");
            System.out.println("This is your Account Panel" + "[Username]" +
                            "\nHere you can make changes to your account.");
            System.out.println("Enter a number to select from the options below;\n");
            System.out.println("[1] Change username\t\t[4] Change address\n" +
                    "[2] Change password\t\t[5] Change phone number\n" +
                    "[3] Change email\n" +
                    "[0] Back to Home\t\t[Q] Quit\n");
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
                case "0":
                    break;
                case "q":
                case "Q":
                    break;
                default:

            }
        } while (true);


    }
}
