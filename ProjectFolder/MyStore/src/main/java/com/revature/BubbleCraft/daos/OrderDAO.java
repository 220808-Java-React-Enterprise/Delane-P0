package com.revature.BubbleCraft.daos;

import com.revature.BubbleCraft.models.Order;
import com.revature.BubbleCraft.models.Product;
import com.revature.BubbleCraft.utils.database.ConnectionFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class OrderDAO implements  CrudDAO<Order>{
    @Override
    public void save(Order order) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders ( id, shop_id, user_id, dateplaced, totalcost, fulfilled ) VALUES ( ?, ?, ?, ?, ?, ? )");
            ps.setObject(1, order.getId());
            ps.setInt(2, order.getShopId());
            ps.setObject(3, order.getUserId());
            ps.setDate(4, Date.valueOf(LocalDate.now()));
            ps.setBigDecimal(5, BigDecimal.valueOf(order.getTotalCost()));
            ps.setBoolean(6, false);


            ps.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();

        }
    }

    public void saveOrderList(Order order) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            Map<Product, Integer> productList = order.getProductList();

            for( Map.Entry<Product, Integer> product: productList.entrySet() ) {
                PreparedStatement ps = con.prepareStatement("INSERT INTO orderedproducts (user_id, product_id, amount, totalcost, order_id ) VALUES ( ?, ?, ?, ?, ? )");

                ps.setObject(1, order.getUserId());
                ps.setInt(2, product.getKey().getId());
                ps.setInt(3, product.getValue());
                ps.setBigDecimal(4, BigDecimal.valueOf((product.getKey().getSellingPrice() * product.getValue())));
                ps.setObject(5, order.getId());

                ps.executeUpdate();
            }

        } catch(SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void update(Order ord) {

    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Order getById(UUID id) {
        return null;
    }

    @Override
    public List<Order> getAll() {

        List<Order> list = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders");

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                list.add( new Order(UUID.fromString(rs.getString("id")), rs.getInt("shop_id"), UUID.fromString(rs.getString("user_id")), rs.getDate("dateplaced").toLocalDate(), rs.getDouble("totalcost"), rs.getBoolean("fulfilled") ) );

            }
            return list;

        } catch(SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    public List<Order> getAllOrdersByShopId( int shopId ) {

        List<Order> list = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE shop_id= ?");
            ps.setInt(1, shopId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                list.add( new Order(UUID.fromString(rs.getString("id")), rs.getInt("shop_id"), UUID.fromString(rs.getString("user_id")), rs.getDate("dateplaced").toLocalDate(), rs.getDouble("totalcost"), rs.getBoolean("fulfilled") ) );

            }
            return list;

        } catch(SQLException e) {
            e.printStackTrace();

        }
        return null;

    }


    public List<Order> getAllOrdersByUserId( UUID userId ) {

        List<Order> list = new LinkedList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE user_id= ?");
            //ps.setString(1, String.valueOf(userId));
            ps.setObject(1, userId);
            ResultSet rs = ps.executeQuery();

            //Just removed datefulfilled from user viewing. //Preventing disaster.....(the column datefulfilled can be null in db but reading a null value will cause the program to kick the user back to the login menu...)

            while(rs.next()) {
                Order order = new Order(UUID.fromString(rs.getString("id")), rs.getInt("shop_id"), UUID.fromString(rs.getString("user_id")), rs.getDate("dateplaced").toLocalDate(), rs.getDouble("totalcost"), rs.getBoolean("fulfilled"));
                list.add(order);
            }
            return list;

        } catch(SQLException e) {
            e.printStackTrace();

        }
        System.out.println("5" + list);
        return null;

    }

    public List<Order> getAllOrderDetailsByOrderId(UUID orderId) {


        List<Order> list = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM orderedproducts WHERE order_id= ?");
            ps.setString(1, String.valueOf(orderId));

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                list.add( new Order(rs.getInt("id"), UUID.fromString(rs.getString("user_id")), rs.getInt("product_id"), rs.getInt("amount"), rs.getDouble("totalcost"), UUID.fromString(rs.getString("order_id")) ) );

            }
            return list;

        } catch(SQLException e) {
            e.printStackTrace();

        }

        return null;

    }


}
