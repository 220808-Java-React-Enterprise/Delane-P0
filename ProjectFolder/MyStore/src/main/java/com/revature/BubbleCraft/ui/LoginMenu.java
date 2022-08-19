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
    private final UserService userService;

    //Creating a store
    private  static final Shop SHOP = LoadShop();
    public static final Scanner input = new Scanner(System.in);

    //Goal implement a guest user when the customer first access the program.
   private static User user = new User(" Guest");

    //Constructor
    public LoginMenu(UserService userService) { this.userService = userService; }

    //START
    @Override
    public void start() {

        System.out.println("Welcome to " + SHOP.getName() + "!");

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
                        break StartScreen;
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

        user = new User("Guest"); //TODO: create a default guest user.

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

        Signup_loop:
        {
            do {
                System.out.println("SIGN UP!" +
                        "\nEnter your information below to sign up.");

                user.setEmail(Validate("Email"));
                user.setName(Validate("Username"));
                user.setPassword(Validate("Password"));

                System.out.println("Is this correct?\n" + user.toString(0) +
                        "\n[1] Yes, continue to login!" +
                        "\n[2] No, redo the signup!" +
                        "\n[3] Quit, your signup information will not be saved!");

                switch (input.nextInt()) {
                    case 1:
                        userService.register(user);
                        System.out.println("Moving to Login...");
                        break Signup_loop;
                    case 2:
                        System.out.println("Restarting Signup...");
                        continue;
                    case 3:
                        Quit();
                    default:
                        System.out.println("Reloading Signup...");

                }

            } while (true);
        }//Signup_loop end.



    }

    //Validate
    public String Validate(String s) {

        boolean valid = false;
        String v;

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

        String email = "";
        String password = "";

        Login: {
            do{

                System.out.println("Login with your email and password.\n(Enter Q to quit.)");


                System.out.print("Email:\t");
                email = input.nextLine();

                System.out.print("Password:\t");
                password = input.nextLine();

                if(email.equalsIgnoreCase("q") || password.equalsIgnoreCase("q")) {
                    Quit();
                }

                user = userService.Login( email, password);

                if( user.getName().equalsIgnoreCase("Guest") ) {

                    System.out.println("User not found!\nPlease check if your email and password are correct.\n");
                }


            }while(user.getName().equalsIgnoreCase("Guest"));
        }

    }
}
