package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.OrderDAO;
import com.revature.BubbleCraft.models.Order;

import java.util.List;
import java.util.UUID;

public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) { this.orderDAO = orderDAO; }

    public void placeOrder(Order order) {

        //Creating an order id for order.
        order.setId(UUID.randomUUID());

        orderDAO.save(order);
        orderDAO.saveOrderList(order);


    }

    public List<Order> getAllOrdersByShopId(int shopId) {
        return orderDAO.getAllOrdersByShopId(shopId);

    }

    public List<Order> getAllOrdersByUserId(UUID userId) {
        return orderDAO.getAllOrdersByUserId(userId);

    }

    public List<Order> getAllOrderDetailsByOrderId(UUID orderId) {
        return orderDAO.getAllOrderDetailsByOrderId(orderId);

    }



}
