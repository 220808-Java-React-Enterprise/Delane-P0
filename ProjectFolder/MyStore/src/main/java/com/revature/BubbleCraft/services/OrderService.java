package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.OrderDAO;
import com.revature.BubbleCraft.daos.UserDAO;
import com.revature.BubbleCraft.models.Order;

import java.io.IOException;

public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) { this.orderDAO = orderDAO; }

    public void placeOrder(Order order) throws IOException {

        orderDAO.save(order);
        orderDAO.saveOrderList(order);


    }

    public void storeOrderDetails() {}



}
