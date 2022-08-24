package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.ShopDAO;
import com.revature.BubbleCraft.models.Customer;
import com.revature.BubbleCraft.models.Product;
import com.revature.BubbleCraft.models.Shop;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class ShopServiceTest {

    private ShopService shopService;
    public final ShopDAO mockDao = mock(ShopDAO.class);

    @Before
    public void setUp() throws Exception {
        this.shopService = new ShopService(mockDao);
    }

    @Test
    public void test_RemoveSoldStock_Case1() {
        Customer customerSpy = spy(Customer.class);
        Shop shopSpy = spy(Shop.class);
        Product productSpy = spy(Product.class);
        productSpy.setId(100);
        Map<Integer,Integer> testMap = new LinkedHashMap<>();
        testMap.put(100, 9999);
        shopSpy.setInventory(testMap);

        customerSpy.addToCart(productSpy,9998);

        shopService.RemoveSoldStock(customerSpy, shopSpy);

        int test = shopSpy.getInventory().get(100);



        Assert.assertEquals(1, test);


    }
    @Test
    public void test_RemoveSoldStock_Case2() {
        Customer customerSpy = spy(Customer.class);
        Shop shopSpy = spy(Shop.class);
        Product productSpy = spy(Product.class);
        productSpy.setId(100);
        Map<Integer,Integer> testMap = new LinkedHashMap<>();
        testMap.put(100, 32985);
        shopSpy.setInventory(testMap);

        customerSpy.addToCart(productSpy,20154);

        shopService.RemoveSoldStock(customerSpy, shopSpy);

        int test = shopSpy.getInventory().get(100);



        Assert.assertEquals(12831, test);


    }

}