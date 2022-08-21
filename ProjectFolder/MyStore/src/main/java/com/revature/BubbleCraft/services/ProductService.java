package com.revature.BubbleCraft.services;

import com.revature.BubbleCraft.daos.ProductDAO;
import com.revature.BubbleCraft.daos.UserDAO;
import com.revature.BubbleCraft.models.Product;

import java.util.List;

public class ProductService {

    //Data fields
    private final ProductDAO productDAO;

    //Constructors
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;

    }

    public List<Product> getProductList() {

        return productDAO.getAll();

    }


}
