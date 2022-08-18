package com.revature.BubbleCraft.daos;

import com.revature.BubbleCraft.models.User;
import com.revature.BubbleCraft.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements CrudDAO<User> {

    @Override
    public void save(User obj) {
        try ( Connection con = ConnectionFactory.getInstance().getConnection()){

            PreparedStatement ps = con.prepareStatement("INSERT INTO users ( id, name, password, email, street, city, state, zip, country, payment_method, role) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getStreet());
            ps.setString(6, obj.getCity());
            ps.setString(7, obj.getState());
            ps.setString(8, obj.getZip());
            ps.setString(9, obj.getCountry());
            ps.setString(10, obj.getPayment_method());
            ps.setString(11, obj.getRole());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User obj) {

    }

    public void delete(String id) {

    }

    public User getById(String id) {
        return null;
    }

    public List<User> getAll() {
        return null;
    }
}
