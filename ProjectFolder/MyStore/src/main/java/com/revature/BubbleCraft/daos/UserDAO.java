package com.revature.BubbleCraft.daos;

import com.revature.BubbleCraft.models.User;
import com.revature.BubbleCraft.utils.customexceptions.NotValidException;
import com.revature.BubbleCraft.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements CrudDAO<User> {

    @Override
    public void save(User obj) {
        try ( Connection con = ConnectionFactory.getInstance().getConnection() ){

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
            ps.setString(10, obj.getPhone());
            ps.setString(11, obj.getRole());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User obj) {

    }

    //For Login
    public User getUserByEmailAndPassword(String email, String password) {
        try(Connection con = ConnectionFactory.getInstance().getConnection() ) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) { return new User(rs.getString("name"), rs.getString("email"), rs.getString("password")); }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void delete(String id) {

    }

    public User getById(String id) {
        return null;
    }

    public List<User> getAll() {
        return null;
    }

    //
    public String findUsername(String username) {
        try ( Connection con = ConnectionFactory.getInstance().getConnection()){

            PreparedStatement ps = con.prepareStatement("SELECT (name) FROM users WHERE name = ?");
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) { return rs.getString( "name" );}
            else { return "NOT FOUND"; }

            //Now that this is set up in the DAO a server method needs to be made to wrap it.

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "NOT FOUND";

    }

    //
    public String findEmail(String email) {
        try ( Connection con = ConnectionFactory.getInstance().getConnection()){

            PreparedStatement ps = con.prepareStatement("SELECT (email) FROM users WHERE email = ?");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) { return rs.getString( "email" );}
            else { return "NOT FOUND"; }

            //Now that this is set up in the DAO a server method needs to be made to wrap it.

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "NOT FOUND";

    }
}
