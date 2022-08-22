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

        /*User guest = new User();
        guest.setName("Guest");

        System.out.println(guest + guest.getName());
        System.out.println("Hello " + guest.getName() + ", welcome to " + storeName + "!");
        */

        StartScreen: {
            while (true) {
                System.out.println("Welcome to " + SHOP.getName() + "!");
                System.out.println( "[1]\tLOGIN\n" +
                                    "[2]\tSIGNUP\n" +
                                    "[Q]\tQUIT");

                switch (new Scanner(System.in).next().charAt(0)) {
                    case '1':
                        Login();
                        break;
                    case '2':
                        Signup();
                        Login();
                        break;
                    case 'Q':
                    case 'q': System.exit(0);
                        continue;
                    default:
                        System.out.println("Invalid entry, please input one of the options shown.");
                        continue;

                }//Switch end
                try {
                    if (!(user.getId() == null || user.getRole().equals("ADMIN"))) {
                        //Calling the mainmenu
                        new MainMenu().start();
                    } else if ((!(user.getId() == null)) && user.getRole().equals("ADMIN")) {
                        //Calling adminmenu
                        new AdminMenu().start();
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

    //LOAD STORE --temporary.
    public static Shop LoadShop() {
        return new Shop("Bubble Craft", "123 Some Street", "City", "State", "12345", "USA", "1-xxx-xxxx", "ydyddtr@bc.org", "Hillary Jenkins", "Hillary Jenkins");
    }

    //SIGNUP
    public void Signup() {

        System.out.println("Signup is in progress.");
        user = new User();
        user.setRole("GUEST");

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
            else if( !v.equals("BCadMIN") ){
                try{
                    if( s.equalsIgnoreCase("email") ){ valid = userService.isValidEmail( v ); }
                    else if( s.equalsIgnoreCase("username") ){ valid = userService.isValidUserName( v ); }
                    else if( s.equalsIgnoreCase("password") ){ valid = userService.isValidPassword( v ); }
                    else { throw new NotValidException("Oops, this fell through the cracks. Code fix needed!"); }

                }catch(NotValidException e) {

                    System.out.println( e.getMessage() );
                }
            }
            else if( !user.getRole().equals("ADMIN") ){

                System.out.println("Welcome new administrator, let's finish your sign up!");
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

                System.out.println("Login with your email and password.\n(Enter Q to quit.)");


                System.out.print("Email:\t");
                email = input.nextLine();

                System.out.print("Password:\t");
                password = input.nextLine();

                if(email.equalsIgnoreCase("q") || password.equalsIgnoreCase("q")) {
                    return;
                }

                Navigation.user = userService.Login( email, password);

                if( user.getName().equalsIgnoreCase("Guest") ) {

                    System.out.println("User not found!\nPlease check if your email and password are correct.\n");
                }

                System.out.println(); //for spacing

            }while(Navigation.user.getId() == null); //TODO find a different while condition.
        }

    }
}
