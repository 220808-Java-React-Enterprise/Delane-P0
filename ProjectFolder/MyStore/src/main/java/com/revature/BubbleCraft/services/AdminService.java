package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.UserDAO;

public class AdminService extends UserService{
    public AdminService(UserDAO userDAO) {
        super(userDAO);
    }
}
