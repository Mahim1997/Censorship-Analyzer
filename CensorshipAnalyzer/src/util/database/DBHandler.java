package util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBHandler {

    static Connection conn = null;

    public static void initialiseDBConnection() {

        try {
            // db parameters  
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:Python Programs/Client Data.db";

            conn = DriverManager.getConnection(url);    // create a connection to the database  

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION PROBLEM!");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("CLASS FINDING PROBLEM");
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("ERROR in closing connection");
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //---------------------------------- Queries ----------------------------------------------
    public static int getLatestReportID() {
        return ReportQueryHandler.getCurrentLastReportID();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    //---------------------------------- Queries ----------------------------------------------    
    public static void testQuery() {
        String sql = "SELECT * FROM Report";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set  
            while (rs.next()) {
                System.out.print("Report: ");
                System.out.println(rs.getString("report_id") + ", " + rs.getString("connection_id") + ", "
                        + rs.getString("time_stamp") + ", " + rs.getString("url"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
