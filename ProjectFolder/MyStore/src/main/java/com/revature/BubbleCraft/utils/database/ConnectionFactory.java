package com.revature.BubbleCraft.utils.database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//Singleton classes have only a single instance of themselves.
public class ConnectionFactory {

    //Creating the instance of this singleton class. The instance must be static.
    private static ConnectionFactory connectionFactory;


    //driver for checking environment, what is installed and accessible ect..
    static {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Adding attributes. attributes do not have to be static. This abstracts the metadata for connecting to the db, url, name, & pass.
    private final Properties props = new Properties();

    //Creating private constructor. Only the public static method in this class will access this constructor.
    private ConnectionFactory() {
        try {
            //Reading data from db file.
            props.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Creating public method. this is how this class will run and interact with others.
    public static ConnectionFactory getInstance() {

        //Instantiating the class. only if it has not been already.
        //Checking if class has been instantiated/
        if(connectionFactory ==null) {
            //Instancing class.
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    //Connecting to the database or throwing an exception if the connection fails.
    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(props.getProperty("url"),
                                                      props.getProperty("username"),
                                                      props.getProperty("password"));
        if ( conn == null ) throw new RuntimeException("Could not establish connection to the database. Fix connection and try again!");
        return  conn;
    }



}
