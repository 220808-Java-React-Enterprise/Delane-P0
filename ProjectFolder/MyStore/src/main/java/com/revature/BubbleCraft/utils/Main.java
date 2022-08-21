/*Program: Project 0 - Console store - BubbleCat
Programmer: Delane Green
Purpose: A mock online store that allows a customer to create an account and 'purchase' products.
Created: 08/10/2022
Last updated: 08/10/2022
* */

package com.revature.BubbleCraft.utils;

import com.revature.BubbleCraft.daos.UserDAO;
import com.revature.BubbleCraft.services.UserService;
import com.revature.BubbleCraft.ui.LoginMenu;
import com.revature.BubbleCraft.ui.MainMenu;
import com.revature.BubbleCraft.ui.ShoppingMenu;


//MAIN CLASS
public class Main {


    //MAIN METHOD
    public static void main(String[] args) {
        /*Testing connections*
        try {
            System.out.println(ConnectionFactory.getInstance().getConnection());
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        */

        //Testing shopping menu
        //new ShoppingMenu().start();
        int i = '0';
        int j = '1';
        Integer I = Integer.valueOf('0');
        Integer J = Integer.valueOf('1');
        System.out.println( i + " " + j + " " + I + " " + J);

        //Creating new Login instance, which takes a UserService as a parameter, which then takes a UserDAO(database access object?) as a parameter.
        //The connection works like using a UPS between an outlet and an appliance?
        //It provides a buffer and sends a warning when something out of the ordinary happens, preventing permanent harm to the appliance?
        new LoginMenu(new UserService((new UserDAO()))).start();



    }



}
