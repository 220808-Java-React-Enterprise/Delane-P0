package com.revature.BubbleCraft.ui;

import com.revature.BubbleCraft.daos.OrderDAO;
import com.revature.BubbleCraft.models.Admin;
import com.revature.BubbleCraft.models.Order;
import com.revature.BubbleCraft.models.Shop;
import com.revature.BubbleCraft.services.OrderService;
import com.revature.BubbleCraft.utils.Navigation;

import java.util.List;
import java.util.Map;

public class AdminMenu extends Navigation implements IMenu{
    private static Admin admin = (Admin) user;
    private static final OrderService orderService = new OrderService( new OrderDAO() );
    @Override
    public void start() {


        AdminMenu:
        {
            do {
                System.out.println("Welcome " + "[ADMIN]" + " to the backstage!\n" +
                        "Here you can handle and the view the workings of your shop.\n" +
                        "Choose from the options below, enter the corresponding number to move to that menu:");

                System.out.println("[1] Account\n[2] Shop\n[3] All Orders\n[4] Restock\n[5] Add New Product\n[X] Logout");
                switch (input.next().charAt(0)) {
                    case '1':
                        break;
                    case '2':
                        break;
                    case '3':  DisplayShopOrderHistory(shop.getId());
                        break;
                    case '4':  RestockShopInventory(shop);
                        break;
                    case '5':
                        break;
                    case '6':
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

        shop.viewInventory();

        int product = input.nextInt();
        String process = input.nextLine();

        if(process.matches("/restock/i")) {

        //WIP

        }

    }

    public void DisplayShopOrderHistory(int shopId) {
        List<Order> orderHistory = orderService.getAllOrdersByShopId( shopId );

        System.out.println( shop.getName() + "'s Order History\n" +
                "\tOrder ID\t\tUser ID\t\tDate Placed\tFulfilled\tDate Fulfilled");
        int tally = 0;

        for(Order order: orderHistory) {

            tally++;
            System.out.println("[" + tally + "] " + order.getId() + "\t" + order.getDatePlaced() + "\t" + order.getDateFulfilled() );
        }

    }


}//Adminmenu class end.
