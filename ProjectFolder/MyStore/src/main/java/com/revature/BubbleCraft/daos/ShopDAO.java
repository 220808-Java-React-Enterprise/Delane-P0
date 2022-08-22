package com.revature.BubbleCraft.daos;

import com.revature.BubbleCraft.models.Product;
import com.revature.BubbleCraft.models.Shop;
import com.revature.BubbleCraft.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ShopDAO implements CrudDAO<Shop> {
    @Override
    public void save(Shop obj) {
        try ( Connection con = ConnectionFactory.getInstance().getConnection() ){

            PreparedStatement ps = con.prepareStatement("INSERT INTO users ( id, name, email, street, city, state, zip, country, phone) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, String.valueOf(UUID.randomUUID()));
            ps.setString(2, obj.getName());
            ps.setString(3, obj.getStreet());
            ps.setString(4, obj.getCity());
            ps.setString(5, obj.getState());
            ps.setString(6, obj.getZip());
            ps.setString(7, obj.getCountry());
            ps.setString(8, obj.getPhone());
            ps.setString(9, obj.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Shop obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    //Used for suppliers
    public Shop getById(String id) {

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM suppliers WHERE id = ?");
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                return new Shop(rs.getString("id"), rs.getString("name"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getString("phone"), rs.getString("email"), rs.getString("manager"), rs.getString("owner"));

            }

        } catch(SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    //Used for shops
    public Shop getById(Integer id) {

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM shops WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                return new Shop(rs.getInt("id"), rs.getString("name"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getString("phone"), rs.getString("email"), rs.getString("manager"), rs.getString("owner"));

            }

        } catch(SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    //USED FOR SHOPS NEAR ME.
    public List<Shop> getNearBy(String zip, String city, String state, String country) {

        List<Shop> list = new ArrayList<>();

        try ( Connection con = ConnectionFactory.getInstance().getConnection() ){

            PreparedStatement ps = con.prepareStatement("SELECT * FROM shops ORDER BY CASE WHEN zip = ? THEN 1 WHEN city = ? THEN 2 WHEN state = ? THEN 3 WHEN country = ? THEN 4 ELSE 5 END");
            ps.setString(1, zip);
            ps.setString(2, city);
            ps.setString(3, state);
            ps.setString(4, country);

            ResultSet rs = ps.executeQuery();

            while( rs.next() ) {
                list.add( new Shop(rs.getString("id"), rs.getString("name"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getString("phone"), rs.getString("email"), rs.getString("manager"), rs.getString("owner")));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Shop> getAll() {
        return null;
    }

    public Map<Integer, Integer> getShopInventory(int shopId ) {

        Map< Integer, Integer> map = new LinkedHashMap<>();

        try ( Connection con = ConnectionFactory.getInstance().getConnection() ){

            PreparedStatement ps = con.prepareStatement("SELECT product_id, amount FROM distributions WHERE shop_id = ?");
            ps.setInt(1, shopId);

            ResultSet rs = ps.executeQuery();

            while( rs.next() ) {
                map.put( rs.getInt("product_id"), rs.getInt("amount"));
            }
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void saveShopInventory(Map<Integer,Integer> inventory, int shopId) {

        try ( Connection con = ConnectionFactory.getInstance().getConnection() ){

            for(Map.Entry<Integer,Integer> map: inventory.entrySet()) {
                PreparedStatement ps = con.prepareStatement("UPDATE distributions SET amount= ? WHERE shop_id= ? AND product_id= ?");
                ps.setInt(1, map.getValue());
                ps.setInt(2, shopId);
                ps.setInt(3, map.getKey());

                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
