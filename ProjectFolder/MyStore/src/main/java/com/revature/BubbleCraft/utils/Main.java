/*Program: Project 0 - Console store - BubbleCat
Programmer: Delane Green
Purpose:
Created: 08/10/2022
Last updated: 08/10/2022
* */

package com.revature.BubbleCraft.utils;

import com.revature.BubbleCraft.ui.LoginMenu;
import com.revature.BubbleCraft.utils.database.ConnectionFactory;

import java.sql.SQLException;

//MAIN CLASS
public class Main {


    //MAIN METHOD
    public static void main(String[] args) {
        try {
            System.out.println(ConnectionFactory.getInstance().getConnection());
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        //*Testing connections*
        new LoginMenu().start();


    }



}
