package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.ShopDAO;
import com.revature.BubbleCraft.daos.UserDAO;
import com.revature.BubbleCraft.models.Customer;
import com.revature.BubbleCraft.models.Shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopService {
    private final ShopDAO shopDAO;

    public ShopService(ShopDAO shopDAO) { this.shopDAO = shopDAO; }

    public List<Shop> getShopsNearMe(Customer customer) {

        return shopDAO.getNearBy(customer.getZip(), customer.getCity(), customer.getState(), customer.getCountry());

    }

    public Shop getShopById(Integer id) {

        return shopDAO.getById( id );

    }

    public Map< Integer, Integer> getShopInventory(int shopId ) {

        return shopDAO.getShopInventory( shopId );

    }
    public void saveShopInventory(Map<Integer,Integer> inventory, int shopId) {
        shopDAO.saveShopInventory( inventory, shopId );

    }

}
