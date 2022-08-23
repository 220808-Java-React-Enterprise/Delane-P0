package com.revature.BubbleCraft.ui;

import com.revature.BubbleCraft.daos.OrderDAO;
import com.revature.BubbleCraft.models.Order;
import com.revature.BubbleCraft.services.OrderService;
import com.revature.BubbleCraft.utils.Navigation;

import java.util.List;
import java.util.UUID;

public class OrderMenu extends Navigation implements IMenu{

    private static final OrderService orderService = new OrderService( new OrderDAO() );

    public OrderMenu() {}

    @Override
    public void start() {

        //Things to be moved over from shopping menu.
    }

    public char SelectOrderSort() {

        System.out.println("Order History by?\n" +
                "[D] Date [C] cost");
        String mP = input.next();
        if(mP.matches("^c|C.")) { mP = "c"; }
        else { mP = "d"; }

        return mP.charAt(0);
    }

    public void ViewCustomerOrders(UUID userId) {
        char ch = SelectOrderSort();

        List<Order> orderList = orderService.getAllOrdersByUserId(userId, ch);
        int i = 0;
        System.out.println("Enter a number from the menu to view that order's details.");
        System.out.println("Shop#\tDate Placed\tCost\t\tFulfilled\t\t\tOrder ID");

        for(Order o: orderList) {
            i++;

            System.out.println( "[" + i + "]\t" + o.getShopId() + "\t" +
                    o.getDatePlaced() + "\t" + o.getTotalCost() + "\t\t" + o.isFulfilled() + "\t" + o.getId() );

        }

        System.out.println("\t\t\t[R} Return");
        try {
            int menuChoice = Integer.parseInt(input.next());
            ViewOrderDetails( orderList.get(menuChoice - 1).getId());
        }
        catch(Exception e) {
            return;
        }
    }
    public void ViewOrderDetails(UUID orderId) {

        List<Order> detailsList = orderService.getAllOrderDetailsByOrderId(orderId);

        //TODO:List<Product> productList = new ArrayList<>();

        int i = 0;
        for(Order o: detailsList) {
            i++;
            System.out.println( "[" + i + "] " + o.getOpId() + "\t" + o.getShopId() + "\t" +
                   o.getAmount() + "\t" + o.getTotalCost() + "\t\t" +  o.getDatePlaced() + "\t" + o.getId() );
        }
    }

}
