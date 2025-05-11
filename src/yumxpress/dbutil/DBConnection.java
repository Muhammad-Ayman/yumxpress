package yumxpress.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {

    private static Connection conn;

    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            // Define MySQL connection parameters (replace with actual user, password, and database name)
            String jdbcUrl = "jdbc:mysql://localhost:3306/yumxpress_db?useSSL=false&allowPublicKeyRetrieval=true";
            String username = "root"; // Replace with your MySQL username
            String password = ""; // Replace with your MySQL password
            
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            JOptionPane.showMessageDialog(null, "MySQL Connection opened successfully");

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found!");
            ex.printStackTrace();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Cannot open the MySQL connection. Check DB server, URL, username, password, and database existence.");
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                // JOptionPane.showMessageDialog(null, "MySQL Connection closed successfully"); // Optional: for debugging
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Cannot close the MySQL connection");
            ex.printStackTrace();
        }
    }
}

