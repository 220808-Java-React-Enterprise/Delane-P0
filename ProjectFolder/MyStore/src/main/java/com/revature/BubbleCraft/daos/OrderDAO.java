package com.revature.BubbleCraft.daos;

import com.revature.BubbleCraft.models.Order;
import com.revature.BubbleCraft.models.Product;
import com.revature.BubbleCraft.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class OrderDAO implements  CrudDAO<Order>{
    @Override
    public void save(Order order) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders ( id, shop_id, dateplaced, fulfilled, datefulfilled ) VALUES ( ?, ?, ?, ?, ? )");
            ps.setInt(1, order.getId());
            ps.setInt(2, order.getShopId());
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setBoolean(4, false);
            ps.setDate(5, Date.valueOf(LocalDate.now())); //TODO: remove placeholder change df to null.

            ps.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();

        }
    }

    public void saveOrderList(Order order) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            Map<Product, Integer> productList = order.getProductList();

            for( Map.Entry<Product, Integer> product: productList.entrySet() ) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO orderedproducts (user_id, product_id, amount, order_id ) VALUES ( ?, ?, ?, ? )");
                //ps.setInt(1, order.getOpId());
                ps.setObject(1, order.getUserId());
                ps.setInt(2, product.getKey().getId());
                ps.setInt(3, product.getValue());
                ps.setInt(4, order.getId());

                ps.executeUpdate();
            }

        } catch(SQLException e) {
            e.printStackTrace();

        }}

    @Override
    public void update(Order ord) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Order getById(String id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
