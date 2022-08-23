package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.UserDAO;
import com.revature.BubbleCraft.models.User;
import com.revature.BubbleCraft.utils.customexceptions.NotValidException;
import com.revature.BubbleCraft.utils.customexceptions.NullUserException;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) { this.userDAO = userDAO; }
    public void register(User user) { userDAO.save(user); }
    public void UpdateUser(User user) { userDAO.update(user); }

    //Login method.
    public User Login(String email, String password) {
        User user = userDAO.getUserByEmailAndPassword( email, password);
        if(!(user == null)) {
            return user;
        }
        else{ throw new NullUserException("\nUser not found!\nPlease check if your email and password are correct.\n\n");
        }

    }

    //Validate user email.
    public boolean isValidEmail(String email){

        boolean check = email.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+");
        if(!check) { throw new NotValidException( "That is not a valid email.\nPlease enter a valid email address below or enter q to quit.\n\n" ); }

        //Checking for already registered email.
        if(!userDAO.findEmail(email).equalsIgnoreCase("NOT FOUND")) {
            throw new NotValidException("That email is already registered!\n\n");
        }

        return check;
    }

    //Validate user name.
    public boolean isValidUserName(String username){
        boolean check = username.matches("^[a-zA-Z0-9_-]{3,15}$");
        if(!check) {
            throw new NotValidException( "That is not a valid username." +
                    "\nA valid username can use letters, numbers, plus _ or -, and be between 3-15 characters long." +
                    "\nPlease enter a valid username below or enter q to quit.\n\n" );
        }
        if(!isUsernameFree(username)) { check = false; }

        return check;
    }

    public boolean isUsernameFree(String username) {

        //Checking if username is taken username.
        if(!userDAO.findUsername(username).equals("NOT FOUND")) {
            throw new NotValidException( "Sorry that username is taken!\n\n");

        }

        return true;
    }


    //Validate user password.
    public boolean isValidPassword(String password){
        boolean check = password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");
        if(!check) { throw new NotValidException( "That is not a valid password." +
                "\nA valid password must contain upper and lower case letters, numbers, and at least one symbol." +
                "\nPlease enter a valid password below or enter q to quit.\n\n" ); }

        return check;
    }

    public boolean isValidPhone(String phone) {
        boolean check = phone.matches("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$");
        if(!check) {throw new NotValidException("That is not a valid phone number.\nDo not enter the extention, enter the ten digits of your phone number.\n\n");}
        return check;
    }

    //Lookup find a user by name
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);

    }


}
