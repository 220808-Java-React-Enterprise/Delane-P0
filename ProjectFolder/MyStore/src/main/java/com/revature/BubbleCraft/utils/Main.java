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


//MAIN CLASS
public class Main {


    //MAIN METHOD
    public static void main(String[] args) {

        new LoginMenu(new UserService((new UserDAO()))).start();



    }



}
