package com.revature.BubbleCraft.daos;

import com.revature.BubbleCraft.models.Admin;
import com.revature.BubbleCraft.models.Customer;
import com.revature.BubbleCraft.models.User;
import com.revature.BubbleCraft.utils.database.ConnectionFactory;
import sun.util.resources.LocaleData;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class UserDAO implements CrudDAO<User> {

    @Override
    public void save(User obj) {
        try ( Connection con = ConnectionFactory.getInstance().getConnection() ){

            PreparedStatement ps = con.prepareStatement("INSERT INTO users ( id, name, password, email, street, city, state, zip, country, phone, role, registered, lastlogin) VALUES ( gen_random_uuid(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            //ps.setString(1, String.valueOf(UUID.randomUUID())); //Generating random UUID when user is saved to the db. //TODO: Move UUID creation to when user signup and verify against already saved UUIDs to avoid conflicts.
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getPassword());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getStreet());
            ps.setString(5, obj.getCity());
            ps.setString(6, obj.getState());
            ps.setString(7, obj.getZip());
            ps.setString(8, obj.getCountry());
            ps.setString(9, obj.getPhone());
            ps.setString(10, obj.getRole());
            ps.setDate(11, Date.valueOf(LocalDate.now()));
            ps.setDate(12, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User obj) {
        try ( Connection con = ConnectionFactory.getInstance().getConnection() ){

            PreparedStatement ps = con.prepareStatement("INSERT INTO users ( name, password, email, street, city, state, zip, country, phone, role, lastlogin) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getPassword());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getStreet());
            ps.setString(5, obj.getCity());
            ps.setString(6, obj.getState());
            ps.setString(7, obj.getZip());
            ps.setString(8, obj.getCountry());
            ps.setString(9, obj.getPhone());
            ps.setString(10, obj.getRole());
            ps.setDate(12, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //For Login
    public User getUserByEmailAndPassword(String email, String password) {

        try(Connection con = ConnectionFactory.getInstance().getConnection() ) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                if(!rs.getString("role").equals("ADMIN")) {
                    return new Customer(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getString("phone"), rs.getString("role"), rs.getDate("registered").toLocalDate(), rs.getDate("lastlogin").toLocalDate());

                }
                else {
                    return new Admin(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getString("phone"), rs.getString("role"), rs.getDate("registered").toLocalDate(), rs.getDate("lastlogin").toLocalDate());

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void delete(UUID userId) {
        try ( Connection con = ConnectionFactory.getInstance().getConnection()){

            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setObject(1, userId);

            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User getById(UUID id) {
        return null;
    }
    public User getUserByUsername(String username) {
        try ( Connection con = ConnectionFactory.getInstance().getConnection()){

            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE name = ?");
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                if(!rs.getString("role").equals("ADMIN")) {
                    return new Customer(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getString("phone"), rs.getString("role"), rs.getDate("registered").toLocalDate(), rs.getDate("lastlogin").toLocalDate());

                }
                else {
                    return new Admin(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getString("phone"), rs.getString("role"), rs.getDate("registered").toLocalDate(), rs.getDate("lastlogin").toLocalDate());

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<User> getAll() {
        return null;
    }

    //FIND USERNAME for validation
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

    //Save Cart

}
