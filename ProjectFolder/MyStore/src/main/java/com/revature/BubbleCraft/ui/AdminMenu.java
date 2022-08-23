package com.revature.BubbleCraft.ui;

import com.revature.BubbleCraft.daos.OrderDAO;
import com.revature.BubbleCraft.models.Admin;
import com.revature.BubbleCraft.models.Order;
import com.revature.BubbleCraft.models.Shop;
import com.revature.BubbleCraft.models.User;
import com.revature.BubbleCraft.services.OrderService;
import com.revature.BubbleCraft.utils.Navigation;
import com.revature.BubbleCraft.utils.customexceptions.NullUserException;

import java.util.List;

public class AdminMenu extends Navigation implements IMenu{
    private final Admin admin;
    private static final OrderService orderService = new OrderService( new OrderDAO() );

    public AdminMenu(Admin admin) { this.admin = (Admin) user;}
    @Override
    public void start() {


        AdminMenu:
        {
            do {
                System.out.println("Welcome " + admin.getName() + " to the backstage!\n" +
                        "Here you can handle and the view the workings of your shop.\n" +
                        "Choose from the options below, enter the corresponding number to move to that menu:");

                System.out.println("[1] Account\n[2] Shop Information\n[3] All Orders\n[4] Restock\n[5] Add New Product\n[6] Lookup users\n[X] Logout");
                switch (input.next().charAt(0)) {
                    case '1':   new AccountMenu( admin ).start();
                        break;
                    case '2':
                        break;
                    case '3':  DisplayShopOrderHistory(shop.getId());
                        break;
                    case '4':  RestockShopInventory(shop);
                        break;
                    case '5':
                        break;
                    case '6':   LookUpCustomerByUsername();
                        break;
                    case 'x':
                    case 'X':
                        return;
                    default:

                }//switch end


            } while (true);
        }//Adminmenu break label end.


    }


    public void RestockShopInventory(Shop shop) {//TODO
        int product = 0;
        String process = "";

        do {

            shop.viewInventory();
            try {
                product = input.nextInt();
                process = input.next();
            }
            catch (Exception e) {
                System.out.println("Input was invalid!");
                continue;

            }

            if (process.matches("restock")) {

                //WIP
                if (shop.getInventory().containsKey(product)) {
                    System.out.println("How much are you adding?");
                    int amount = input.nextInt();

                    shop.RestockInventory(product, amount);

                    System.out.println("Restocked " + amount + " items to the shop's inventory!");


                } else {

                    System.out.println("This shop does not have that product.\nGo to Add Products on the Backstage Menu to add new products to your shop!");


                }

            } else if (process.matches(".details")) {
            } else {
                System.out.println("Process Unknown! Did you misspell a word?");

            }
        }while(!(product == 0));

    }

    public void DisplayShopOrderHistory(int shopId) {
        List<Order> orderHistory = orderService.getAllOrdersByShopId( shopId );

        System.out.println( "\t" + shop.getName() + "'s Order History\n" +
                "Order#\tShop#\tDate Placed\tFulfilled\tDate Fulfilled");
        int tally = 0;

        for(Order order: orderHistory) {
            boolean filled = order.getDateFulfilled() != null;

            tally++;
            System.out.println("[" + tally + "] " + order.getId() + "\t\t" + order.getShopId() + "\t" + order.getDatePlaced() + "\t" + filled + "\t" + order.getDateFulfilled() + "\n" );
        }



    }
    public void DisplayOrderDetails() {}

    public void LookUpCustomerByUsername() {
        do {
            System.out.println("Enter the username of the customer you would like to find: \n[R] Return");
            String username = input.next();

            if (username.charAt(0) == 'r' || username.charAt(0) == 'R') {
                return;
            }

            try {//TODO: go into userservice and thow exception if null.
                User lookUp = userService.getUserByUsername(username);
                System.out.println(lookUp);

            } catch (NullUserException e) {
                e.getMessage();

            }

        }while(true);


    }


}//Adminmenu class end.
