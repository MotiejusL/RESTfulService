/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class for getting a database connection
 *
 * @author motsa
 */
public class Database {

    /**
     * Method that gets and returns database connection
     *
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "");

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql" + "://localhost:3306/restservice", connectionProps);
        return conn;
    }
}
