package com.revature.BubbleCraft.utils;


import com.revature.BubbleCraft.daos.ProductDAO;
import com.revature.BubbleCraft.daos.ShopDAO;
import com.revature.BubbleCraft.daos.UserDAO;
import com.revature.BubbleCraft.models.Product;
import com.revature.BubbleCraft.models.Shop;
import com.revature.BubbleCraft.models.User;
import com.revature.BubbleCraft.services.ProductService;
import com.revature.BubbleCraft.services.ShopService;
import com.revature.BubbleCraft.services.UserService;
import com.revature.BubbleCraft.ui.IMenu;

import java.util.*;

//This class is to hold all the static properties, methods, and local dependencies needed for the program.
public class Navigation implements IMenu {
    //Program constants
    protected static final UserService userService = new UserService( new UserDAO() );
    private static final ShopService shopService = new ShopService( new ShopDAO() );
    protected static final ProductService productService = new ProductService( new ProductDAO() );

    //Creating a store
    protected static Shop shop = LoadShop();
    private static final List<Product> PRODUCT_LIST = productService.getProductList();


    public static final Scanner input = new Scanner(System.in);

    //Goal implement a guest user when the customer first access the program.
    private static final User GUEST = GenerateGuestUser();
    protected static User user;

    //Constructor
    public Navigation() {}


    //METHODS

    //QUIT
    public void Exit() {

        user = new User("Guest"); //TODO: create a default guest user.

        System.out.println("\nThanks for visiting " + shop.getName() + "!\nHave a great day!\n");
        //input.close();  //??
        System.exit(0);

    }

    public void Pause() {
        String pause = input.next();

    }

    //LOGOUT
    public void Logout() {
        //TODO;
        //Update user information to the DB.
        //Switch user back to guest user.
        //Return to login menu.

    }


    //LOAD STORE
    public static Shop LoadShop() {
        return shopService.getShopById(1); //TODO
    }

    //GEN GUEST USER //TODO
    private static User GenerateGuestUser() {
        return new User("Guest");

    }

    //CONVERT TO PRODUCT
    public static Product ConvertIntToProduct(Integer id) {

        for(Product p: PRODUCT_LIST) {
            if( p.getId() == id ) { return p; }

        }
        return PRODUCT_LIST.get(0);
    }
    public static List<Product> ConvertIntToProduct(List<Integer> list) {

        List<Product> products = new LinkedList<>();

        for(Product p: PRODUCT_LIST) {
            if( list.contains(p.getId()) ) { products.add( p ); }

        }
        return products;
    }

    public static Map<Product,Integer> ConvertIntToProduct(Map<Integer,Integer> map) {
        Map<Product,Integer> products = new LinkedHashMap<>();

        for(Product p: PRODUCT_LIST) {
            if( map.containsKey( p.getId() ) ) {

                products.put( p, map.get( p.getId() ) );

            }

        }
        return products;
    }


    @Override
    public void start() {
    }
}
