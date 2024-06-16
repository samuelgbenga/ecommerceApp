package main.ecommerce.app.connection;

import lombok.Getter;
import main.ecommerce.app.util.ConfigUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Getter
public class DBConnection {


    // load config file



    //GET connection
    private final Connection connection;

    public DBConnection() throws ClassNotFoundException, SQLException {

        // 1. Register driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. Create Connection
        this.connection = DriverManager.getConnection(ConfigUtil.getProperty("url"),
                ConfigUtil.getProperty("user"), ConfigUtil.getProperty("password"));
    }

    //Close Connection
    public void closeConnection() throws SQLException {
        if(this.connection != null) {
            this.connection.close();
        }

    }
//        public static void main(String[] args) {
//            // Test the utility class
//            System.out.println("Database URL: " + ConfigUtil.getProperty("url"));
//            System.out.println("Database User: " + ConfigUtil.getProperty("user"));
//            System.out.println("Database Password: " + ConfigUtil.getProperty("password"));
//        }


}
