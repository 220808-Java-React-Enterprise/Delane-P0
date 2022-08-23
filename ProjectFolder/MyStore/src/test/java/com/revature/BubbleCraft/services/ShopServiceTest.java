package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.ShopDAO;
import com.revature.BubbleCraft.models.Shop;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ShopServiceTest extends TestCase {

    private ShopService shopService;
    public final ShopDAO mockDao = mock(ShopDAO.class);

    @Before
    public void setUp() throws Exception {
        this.shopService = new ShopService(mockDao);
    }


    @Test
    public void getShopById() {

        int validId = 1;

        Shop shop = mockDao.getById(validId);

        Assert.assertNotNull(shop);
    }

    @Test
    public void getShopInventory() {
    }

    @Test
    public void saveShopInventory() {
    }
}