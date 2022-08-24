package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.OrderDAO;
import com.revature.BubbleCraft.models.Customer;
import com.revature.BubbleCraft.models.Order;
import com.revature.BubbleCraft.models.Shop;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.postgresql.util.PSQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest extends TestCase {

    OrderService orderService;

    OrderDAO mockDAO = mock(OrderDAO.class);

    @Before
    public void setUp() throws Exception {
        this.orderService = new OrderService(mockDAO);
    }

    @Test
    public void test_placeOrder_CheckIdGeneration() {
        Order orderSpy = spy(Order.class);
        orderService.placeOrder(orderSpy);

        Assert.assertNotNull(orderSpy.getId());
    }

    @Test
    public void test_CreateOrder_ReturnsAnOrder() {
        Customer customerSpy = spy(Customer.class);
        Shop shopSpy = spy((Shop.class));
        Order testOrder = orderService.CreateOrder(customerSpy,shopSpy);

        Assert.assertNotNull(testOrder);

    }

    @Test
    public void test_CreateOrder_OrderAttributesNotNull() {
        Customer customerSpy = spy(Customer.class);
        Shop shopSpy = spy((Shop.class));
        shopSpy.setId(123);
        Order testOrder = orderService.CreateOrder(customerSpy,shopSpy);


        Assert.assertNotNull(testOrder.getShopId());

    }
}