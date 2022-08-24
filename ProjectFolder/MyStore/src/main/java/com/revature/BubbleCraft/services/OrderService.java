package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.OrderDAO;
import com.revature.BubbleCraft.models.Customer;
import com.revature.BubbleCraft.models.Order;
import com.revature.BubbleCraft.models.Shop;

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

    public List<Order> getAllOrdersByUserId(UUID userId, char ch) {
        return orderDAO.getAllOrdersByUserId(userId, ch);

    }

    public List<Order> getAllOrderDetailsByOrderId(UUID orderId) {
        return orderDAO.getAllOrderDetailsByOrderId(orderId);

    }

    public Order CreateOrder(Customer customer, Shop shop) {

        Order order = new Order();
        order.setUserId(customer.getId());
        order.setProductList( customer.getCart() );
        order.setShopId(shop.getId());
        order.setTotalCost(customer.GetCartTotal());

        return order;
    }




}
