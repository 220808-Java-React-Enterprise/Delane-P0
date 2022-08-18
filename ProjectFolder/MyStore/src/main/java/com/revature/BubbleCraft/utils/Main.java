/*Program: Project 0 - Console store - BubbleCat
Programmer: Delane Green
Purpose:
Created: 08/10/2022
Last updated: 08/10/2022
* */

package com.revature.BubbleCraft.utils;

import com.revature.BubbleCraft.daos.UserDAO;
import com.revature.BubbleCraft.services.UserService;
import com.revature.BubbleCraft.ui.LoginMenu;
import com.revature.BubbleCraft.utils.database.ConnectionFactory;

import java.sql.SQLException;

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

        //Creating new Login instance, which takes a UserService as a parameter, which then takes a UserDAO(database access object?) as a parameter.
        //The connection works like using an UPS between an outlet and an appliance?
        //It provides a buffer and sends a warning when something out of the ordinary happens, preventing permanent harm to the appliance?
        new LoginMenu(new UserService((new UserDAO()))).start();


    }



}
