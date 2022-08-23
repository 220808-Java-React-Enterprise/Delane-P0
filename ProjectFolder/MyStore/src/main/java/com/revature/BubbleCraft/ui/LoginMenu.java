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
import com.revature.BubbleCraft.utils.Navigation;
import com.revature.BubbleCraft.utils.customexceptions.NotValidException;
import com.revature.BubbleCraft.utils.customexceptions.NullUserException;

public class LoginMenu extends Navigation implements IMenu {

    //Constants

    //Creating a store
    private  static final Shop SHOP = LoadShop();
    public static final Scanner input = new Scanner(System.in);


    //Constructor
    public LoginMenu(UserService userService) {

    }

    //START
    @Override
    public void start() {

        StartScreen: {
            while (true) {
                System.out.println("\n\nWelcome to " + SHOP.getName() + "!\n");
                System.out.println( "\t  [1] LOGIN\n" +
                                    "\t  [2] SIGNUP\n" +
                                    "\t  [X] Exit");

                switch (new Scanner(System.in).next().charAt(0)) {
                    case '1':
                        Login();
                        break;
                    case '2':
                        Signup();
                        Login();
                        break;
                    case 'X':
                    case 'x': System.exit(0);
                        continue;
                    default:
                        System.out.println("Invalid entry, please input one of the options shown.");
                        continue;

                }//Switch end
                try {
                    if(!(user.getId() == null)) {

                        if (!user.getRole().equals("ADMIN")) {

                            //Calling the mainmenu
                            Customer customer = (Customer) user;
                            new MainMenu(customer).start();

                        } else {

                            //Calling adminmenu
                            Admin admin = (Admin) user;
                            new AdminMenu(admin).start();
                        }
                    }
                } catch (NullPointerException e) {
                    continue;

                }
            }//while loop end.
        }// break label end

    }//Start Method end.

    //QUIT
    public void Quit() {

        user = new User("Guest"); //TODO: create a default guest user.

        System.out.println("\nThanks for visiting " + SHOP.getName() + "!\nHave a great day!\n");
        input.close();  //??
        System.exit(0);

    }

    //SIGNUP
    public void Signup() {

        user = new User();
        user.setRole("GUEST");

        do {
            System.out.println("\n\n\t\t\tSIGN UP!" +
                    "\nEnter your information below to sign up.");

            user.setEmail(Validate("Email"));
            user.setName(Validate("Username"));
            user.setPassword(Validate("Password"));

            System.out.println("Is this correct?\n\n" + user.toString(0) +
                    "\n\n[1] Yes, continue to login!" +
                    "\n[2] No, redo the signup!" +
                    "\n[3] Quit, your signup information will not be saved!");

            switch (input.nextInt()) {
                case 1:
                    userService.register(user);
                    System.out.println("Moving to Login...\n");
                    input.nextLine();   //Buffer to prevent the login email input from firing early.
                    return;
                case 2:
                    System.out.println("Restarting Signup...");
                    continue;
                case 3:
                    return;
                default:
                    System.out.println("Reloading Signup...");

            }

        } while (true);



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
            else if( !v.equals("BCadMIN") ){
                try{
                    if( s.equalsIgnoreCase("email") ){ valid = userService.isValidEmail( v ); }
                    else if( s.equalsIgnoreCase("username") ){ valid = userService.isValidUserName( v ); }
                    else if( s.equalsIgnoreCase("password") ){ valid = userService.isValidPassword( v ); }
                    else { throw new NotValidException("Oops, this fell through the cracks...."); }

                }catch(NotValidException e) {

                    System.out.println( e.getMessage() );
                }
            }
            else if( !user.getRole().equals("ADMIN") ){

                System.out.println("Welcome new administrator, let's finish your sign up!\n");
                user.setRole("ADMIN");
            }

        } while(!valid);

        return v;

    }
    //ADMIN SIGNUP
    private void AdminSignup() {
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
                    System.out.println("Moving to Login...\n");
                    input.nextLine();   //Buffer to prevent the login email input from firing early.
                    return;
                case 2:
                    System.out.println("Restarting Signup...");
                    continue;
                case 3:
                    Quit();
                default:
                    System.out.println("Reloading Signup...");

            }

        } while (true);

    }   //Unused


    //LOGIN
    public void Login() {

        String email = "";
        String password = "";

        Login: {
            do{

                System.out.println("\nLogin with your email and password.\n(Enter X to exit.)");


                System.out.print("\nEmail:\t");
                email = input.nextLine();

                if(email.matches("^[x\\|X]$")) {
                    return;
                }

                System.out.print("\nPassword:\t");
                password = input.nextLine();

                if(password.matches("^[x\\|X]$")) {
                    return;
                }

                try {
                    Navigation.user = userService.Login(email, password);
                    return;
                } catch (NullUserException e) {
                    System.out.println(e.getMessage());

                }

                System.out.println(); //for spacing

            }while(true);
        }

    }
}
