package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBConnection {
    private Connection connection = null;
    
    public Connection connect() throws SQLException {    
        if(this.connection==null || this.connection.isClosed()){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection =  (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/database?user=root&password=123");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        }
        return this.connection;
    }
}

