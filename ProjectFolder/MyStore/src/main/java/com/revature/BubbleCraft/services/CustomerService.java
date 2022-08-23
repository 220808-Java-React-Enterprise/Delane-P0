package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.UserDAO;

public class CustomerService extends UserService{
    public CustomerService(UserDAO userDAO) {
        super(userDAO);
    }
}
