package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.UserDAO;
import com.revature.BubbleCraft.models.User;
import com.revature.BubbleCraft.utils.customexceptions.NotValidException;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) { this.userDAO = userDAO; }
    /*/Because service is the go between for the db and frontend of the program all calls to and from the db must be filtered through it.
    A go between is very basically what an api is so the services classes can be considered apis./*/
    public void register(User user) {
        //Wrapping the save method from UserDAO and passing it a user from the loginmenu class.
        userDAO.save(user);
    }

    //Login method.
    public User Login(String email, String password) {
        User user = userDAO.getUserByEmailAndPassword( email, password);
        if(!(user == null)) {
            return user;
        }
        return null;

    }

    //Validate user email.
    public boolean isValidEmail(String email){

        boolean check = email.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+");
        if(!check) { throw new NotValidException( "That is not a valid email.\nPlease enter a valid email address below or enter q to quit." ); }

        //Checking for already registered email.
        if(!userDAO.findEmail(email).equalsIgnoreCase("NOT FOUND")) {
            throw new NotValidException("That email is already registered!");
        }

        return check;
    }

    //Validate user name.
    public boolean isValidUserName(String username){
        boolean check = username.matches("^[a-zA-Z0-9_-]{3,15}$");
        if(!check) {
            throw new NotValidException( "That is not a valid username." +
                    "\nA valid username can use letters, numbers, plus _ or -, and be between 3-15 characters long." +
                    "\nPlease enter a valid username below or enter q to quit." );
        }

        //Checking if username is taken username.
        if(!userDAO.findUsername(username).equals("NOT FOUND")) {
            throw new NotValidException( "Sorry that username is taken!");
        }

        return check;
    }

    //Validate user password.
    public boolean isValidPassword(String password){
        boolean check = password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");
        if(!check) { throw new NotValidException( "That is not a valid password." +
                "\nA valid password must contain upper and lower case letters, numbers, and at least one symbol." +
                "\nPlease enter a valid password below or enter q to quit." ); }

        return check;
    }

    //Lookup find a user by name
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);

    }


}
