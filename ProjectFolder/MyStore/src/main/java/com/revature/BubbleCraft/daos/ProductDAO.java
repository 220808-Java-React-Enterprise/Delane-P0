package com.revature.BubbleCraft.daos;

import com.revature.BubbleCraft.models.Product;
import com.revature.BubbleCraft.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements CrudDAO<Product> {
    @Override
    public void save(Product obj) {

    }

    @Override
    public void update(Product obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Product getById(String id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        try ( Connection con = ConnectionFactory.getInstance().getConnection() ) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products ORDER BY type DESC");

            ResultSet rs = ps.executeQuery();


            while( rs.next() ) {

                Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getString("color"), rs.getString("scent"), rs.getString("size"), rs.getString("weight"), rs.getString("brand"), rs.getString("description"), rs.getDouble("selling_price"), rs.getDouble("cost_price"), rs.getString("supplier_id"));

                productList.add(product);

            }

        } catch( SQLException e ) {
            e.printStackTrace();

        }

        return productList;
    }
}
