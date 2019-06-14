 
package hall_management.db.connection;

import java.sql.*;
import hall_management.config.Config;


 
public class MyConnection {
    private final String username ;
    private final String password ;
    private Connection connection ;
    
    private final String CONN_STRING = "jdbc:oracle:thin:@localhost:1521:orcl";
    
    public MyConnection() {
        this.username = Config.db_user_name;
        this.password = Config.db_user_pass;
        connection = null; 
    }
    
    public Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(CONN_STRING, username, password);
            } catch (SQLException e)
            {
                System.out.println("Connection Failed (In getConnection of MyConnection class.. )! Check it from console");
                e.printStackTrace();
            }
        }

        return connection;
    }
    
    public void closeConnection()
    {
        try
        {
            if (connection != null)
            {
                connection.close();
                connection = null;
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
}
