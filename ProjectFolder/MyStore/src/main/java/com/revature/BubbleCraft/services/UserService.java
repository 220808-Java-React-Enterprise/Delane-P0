package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.utils.customexceptions.NotValidException;

public class UserService {

    //Validate user email.
    public boolean isValidEmail(String email){

        boolean check = email.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+");
        if(!check) { throw new NotValidException( "That is not a valid email.\nPlease enter a valid email address below or enter q to quit." ); }

        return check;
    }

    //Validate user name.
    public boolean isValidUserName(String userName){
        boolean check = userName.matches("^[a-zA-Z0-9_-]{3,15}$");
        if(!check) { throw new NotValidException( "That is not a valid username.\nPlease enter a valid username below or enter q to quit." ); }

        return check;
    }

    //Validate user password.
    public boolean isValidPassword(String password){
        boolean check = password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");
        if(!check) { throw new NotValidException( "That is not a valid password.\nPlease enter a valid password below or enter q to quit." ); }

        return check;
    }
}
