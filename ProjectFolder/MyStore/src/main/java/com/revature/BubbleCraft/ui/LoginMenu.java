/*Program: Project 0 - Console store - BubbleCat
Programmer: Delane Green
Purpose:
Created: 08/10/2022
Last updated: 08/10/2022
* */
package com.revature.BubbleCraft.ui;

//IMPORTS
import java.util.Scanner;
import com.revature.BubbleCraft.models.*;
import com.revature.BubbleCraft.services.UserService;
import com.revature.BubbleCraft.utils.customexceptions.NotValidException;

public class LoginMenu implements IMenu {

    //Constants
    private static final UserService userService = new UserService();
    private  static final Shop SHOP = LoadShop();
    public static final Scanner input = new Scanner(System.in);

    //Constructor
    public LoginMenu() {}

    //START
    @Override
    public void start() {
        //Creating a store
        Shop shop = LoadShop();

        System.out.println("Welcome to " + shop.getName() + "!");

        StartScreen: {
            while (true) {
                System.out.println( "[1]\tLOGIN\n" +
                                    "[2]\tSIGNUP\n" +
                                    "[Q]\tQUIT");

                switch (new Scanner(System.in).next().toLowerCase().charAt(0)) {
                    case '1':
                        Login();
                        break StartScreen;
                    case '2':
                        Signup();
                        Login();
                        break;
                    case 'q':
                        Quit();
                    default:
                        System.out.println("Invalid entry, please input one of the options shown.");
                        break StartScreen;

                }
            }
        }

        /*User guest = new User();
        guest.setName("Guest");

        System.out.println(guest + guest.getName());
        System.out.println("Hello " + guest.getName() + ", welcome to " + storeName + "!");
        */
    }

    //QUIT
    public void Quit() {


        System.out.println("\nThanks for visiting " + SHOP.getName() + "!\nHave a great day!\n");
        input.close();  //??
        System.exit(0);

    }

    //LOAD STORE --temporary.
    public static Shop LoadShop() {
        return new Shop("Bubble Craft", "123 Some Street", "City", "State", "12345", "1-xxx-xxxx");
    }

    //SIGNUP
    public void Signup() {

        System.out.println("Signup is in progress.");
        User user = new User();

        do {
            System.out.println("SIGN UP!" +
                    "\nEnter your information below to sign up.");

            user.seteMail(Validate("Email"));
            user.setName(Validate("Username"));
            user.setPassword(Validate("Password"));

            System.out.println("Is this correct?\n" + user.toString(0) +
                    "\n[1] Yes, continue to login!" +
                    "\n[2] No, redo the signup!" +
                    "\n[3] Quit, your signup information will not be saved!");

            switch (input.nextInt()) {
                case 1:

                    System.out.println("Moving to Login...");
                    break;
                case 2:
                    System.out.println("Restarting Signup...");
                    continue;
                case 3:
                    Quit();
                default:
                    System.out.println("Reloading Signup...");

            }

        } while (true);



    }

    //Validate
    public String Validate(String s) {

        boolean valid = false;
        String v = "";

        do {
            System.out.print(s + ":\t");
            v = input.next();

            if( v.equalsIgnoreCase("q")) {
                Quit();
            }
            else {
                try{
                    if( s.equalsIgnoreCase("email") ){ valid = userService.isValidEmail( v ); }
                    else if( s.equalsIgnoreCase("username") ){ valid = userService.isValidUserName( v ); }
                    else if( s.equalsIgnoreCase("password") ){ valid = userService.isValidPassword( v ); }
                    else { throw new NotValidException("Oops, this fell through the cracks. Code fix needed!"); }

                }catch(NotValidException e) {

                    System.out.println( e.getMessage() );
                }

            }

        } while(!valid);

        return v;

    }


    //LOGIN
    public void Login() {
        Login: {
            do{
                String name = "";
                String password = "";

                System.out.print("User name: ");

                break Login; //temp


            }while(true);
        }

    }
}
