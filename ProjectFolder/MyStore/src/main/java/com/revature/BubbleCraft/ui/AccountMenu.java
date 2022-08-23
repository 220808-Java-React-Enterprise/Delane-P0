package com.revature.BubbleCraft.ui;

import com.revature.BubbleCraft.models.Admin;
import com.revature.BubbleCraft.models.Customer;
import com.revature.BubbleCraft.models.User;
import com.revature.BubbleCraft.services.UserService;
import com.revature.BubbleCraft.utils.Navigation;

import java.util.Scanner;

public class AccountMenu extends Navigation implements IMenu{
    private final User accountUser;
    public AccountMenu(Customer customer, UserService userService) {
        this.accountUser = customer;
    }
    public AccountMenu(Admin admin) {
        this.accountUser = admin;
    }

    @Override
    public void start() {

        Scanner input = new Scanner(System.in);

        do {
            System.out.println("\n\n\t\t\tACCOUNT MENU");
            System.out.println("This is your Account Panel " + accountUser.getName() +
                            "\nHere you can make changes to your account.");
            //System.out.println("Enter a number to select from the options below;\n");
            System.out.println("[1] Change username\t\t[4] Change address\n" +
                    "[2] Change password\t\t[5] Change phone number\n" +
                    "[3] Change email\n" +
                    "\n[R] Save & Return\t\t[X] Cancel\n");
            String menuChoice = input.next();

            switch (menuChoice) {
                case "1":   ChangeUsername();
                    break;
                case "2":   ChangePassword();
                    break;
                case "3":   ChangeEmail();
                    break;
                case "4":   ChangeAddress();
                    break;
                case "5":   ChangePhone();
                    break;
                case "r":
                case "R":   SaveAllChanges();
                    return;
                case "x":
                case "X":
                    return;
                 default:
                    System.out.println("\nSorry that's not an available option.\nPlease enter one from the menu.\n(Press enter to continue)\n");
                    input.nextLine();

            }
        } while (true);


    }

    public void ChangePassword() {
        System.out.println("Enter new password");
        String newPassword = input.next();

        if(userService.isValidPassword(newPassword)) {
            accountUser.setPassword(newPassword);
            System.out.println("Password changed! Select save and return on the account menu to save the change.");
            input.nextLine();
            return;
        }

        System.out.println("Password invalid no change made.\n");
        input.nextLine();

    }
    public void ChangeUsername() {
        System.out.println("\nEnter new username");
        String newSetting = input.next();

        if(userService.isValidUserName(newSetting)) {
            accountUser.setName(newSetting);
            System.out.println("Username changed! Select save and return on the account menu to save the change.\n");
            input.nextLine();
            return;
        }

        System.out.println("\nUsername invalid no change made.\n");
        input.nextLine();
    }
    public void ChangeAddress() {
        System.out.println("\t\tADDRESS SCREEN");
        input.nextLine();
        System.out.println("\nEnter new street address");
        String newStreet = input.nextLine();
        System.out.println("Enter new city");
        String newCity = input.nextLine();
        System.out.println("Enter new state");
        String newState = input.nextLine();
        System.out.println("Enter new zip");
        String newZip = input.nextLine();
        System.out.println("Enter new country");
        String newCountry = input.nextLine();


        if(true) {
            accountUser.setAddress( newStreet, newCity, newState, newZip, newCountry);
            System.out.println("\nAddress changed! Select save and return on the account menu to save the change.\n");
            //input.nextLine();
            return;
        }

        System.out.println("\nAddress invalid no change made.\n");
        input.nextLine();
    }
    public void ChangePhone() {
        System.out.println("\nEnter the ten digits of your new phone number.\n");
        String newSetting = input.next();

        if(userService.isValidPhone(newSetting)) {
            accountUser.setPhone(newSetting);
            System.out.println("\nPhone number changed! Select save and return on the account menu to save the change.\n");
            input.nextLine();
            return;
        }

        System.out.println("\nPhone number invalid no change made.\n");
        input.nextLine();
    }
    public void ChangeEmail() {
        System.out.println("\nEnter new email address.\n");
        String newSetting = input.next();

        if(userService.isValidEmail(newSetting)) {
            accountUser.setEmail(newSetting);
            System.out.println("\nEmail address changed! Select save and return on the account menu to save the change.\n");
            input.nextLine();
            return;
        }

        System.out.println("\nEmail address invalid no change made.\n");
        input.nextLine();
    }

    public int SaveCheck() {
        System.out.println("A\nre you sure you want to save your changes?\n\t\t[1] Yes.\t[2] No.\n");
        int mP;
        try{
            mP = input.nextInt();

        } catch(Exception e) {
            return 0;
        }

        return mP;
    }
    public void SaveAllChanges() {

        int pick = SaveCheck();
        if(pick == 1) {
            userService.UpdateUser(accountUser);
            System.out.println("\nChanges saved!\n");
            input.nextLine();
            return;
        }
        System.out.println("\nChanges not saved!\n");
        input.nextLine();

    }
}
