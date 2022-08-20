package com.revature.BubbleCraft.utils;


import com.revature.BubbleCraft.daos.UserDAO;
import com.revature.BubbleCraft.models.Shop;
import com.revature.BubbleCraft.models.User;
import com.revature.BubbleCraft.services.UserService;
import com.revature.BubbleCraft.ui.IMenu;
import com.revature.BubbleCraft.ui.MainMenu;

import java.util.Scanner;

//This class is to hold all the static properties, methods, and local dependencies needed for the program.
public class Navigation implements IMenu {
    //Program constants
    protected final UserService userService = new UserService( new UserDAO() );

    //Creating a store
    protected static Shop shop = LoadShop();
    protected static final MainMenu mainMenu = new MainMenu();



    public static final Scanner input = new Scanner(System.in);

    //Goal implement a guest user when the customer first access the program.
    private static final User GUEST = GenerateGuestUser();
    protected static User user = GUEST;

    //Constructor
    public Navigation() {}


    //METHODS

    //QUIT
    public void Quit() {

        user = new User("Guest"); //TODO: create a default guest user.

        System.out.println("\nThanks for visiting " + shop.getName() + "!\nHave a great day!\n");
        //input.close();  //??
        System.exit(0);

    }

    //LOGOUT
    public void Logout() {
        //TODO;
        //Update user information to the DB.
        //Switch user back to guest user.
        //Return to login menu.

    }


    //LOAD STORE
    public static Shop LoadShop() {
        return new Shop("Bubble Craft", "123 Some Street", "City", "State", "12345", "USA", "1-xxx-xxxx", "ydyddtr@bc.org", "Hillary Jenkins", "Hillary Jenkins");
    }

    //GEN GUEST USER //TODO
    private static User GenerateGuestUser() {
        return new User("Guest");

    }


    @Override
    public void start() {
    }
}
