package com.revature.BubbleCraft.ui;

import com.revature.BubbleCraft.daos.OrderDAO;
import com.revature.BubbleCraft.models.Order;
import com.revature.BubbleCraft.models.Product;
import com.revature.BubbleCraft.services.OrderService;
import com.revature.BubbleCraft.utils.Navigation;

import java.sql.PreparedStatement;
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
        System.out.println("\n\nEnter a number from the menu to view that order's details.");
        System.out.println("\nShop#\tDate Placed\tCost\t Fulfilled\t\t\tOrder ID");

        for(Order o: orderList) {
            i++;

            System.out.println( "[" + i + "]\t" + o.getShopId() + "\t" +
                    o.getDatePlaced() + "\t" + o.getTotalCost() + "\t" + o.isFulfilled() + "\t" + o.getId() );

        }

        System.out.println("\t\t\t\r[R} Return");
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


        System.out.println("\t\t\t\tORDER DETAILS\nShop#\tProduct\t\tColor\tScent\t\t\tAmount\tCost");
        int i = 0;
        for(Order o: detailsList) {
            Product product = ConvertIntToProduct(o.getProductId());
            i++;
            System.out.println( "[" + i + "] " + o.getShopId() + "\t" + product.getName() + "\t" +  product.getColor() + "\t" + product.getScent() + "\t\t\t" +
                   o.getAmount() + "\t" + o.getTotalCost()  );
        }

        System.out.println("\t\t[R] Return\t[X] Main Menu");
        input.nextLine();
        String choice = input.nextLine();
        if(choice.matches("^[r\\|R]")) { ViewCustomerOrders(detailsList.get(0).getUserId()); }
        else { return; }
    }

}
