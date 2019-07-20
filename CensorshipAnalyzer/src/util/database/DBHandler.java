package util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import ui.model.Network;
import ui.model.Report;
import ui.model.User;

public class DBHandler {

    static Connection conn = null;

    public static void openConnection() {

        try {
            // db parameters  
            Class.forName("org.sqlite.JDBC");
//            String url = "jdbc:sqlite:Python Programs/Client Data.db";
            String url = "jdbc:sqlite:New_Python_Programs/Client_DataBase.db";

            conn = DriverManager.getConnection(url);    // create a connection to the database  

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION PROBLEM!");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("CLASS FINDING PROBLEM");

        }

    }

    private static String quote(int s_int) {
        String s = String.valueOf(s_int);
        if (s.charAt(0) != '(') {
            String str = '(' + s + ')';
            return str;
        } else {
            return s;
        }

    }

    private static String quote(String s) {
        if(s == null){
            return s;
        }
        if (s.charAt(0) != '\'') {
            String str = "'" + s + "'";
            return str;
        } else {
            return s;
        }

    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("ERROR in closing connection");
            }
        }
    }


    //---------------------------------- Queries from ReportQueryHandler ... ----------------------------------------------
    public static int getLatestReportID() {
        openConnection();
        int currentLastReportID = ReportQueryHandler.getCurrentLastReportID();
        closeConnection();
        return currentLastReportID;
    }

/*    public static List<Report> getListOfReports(int startIdx, int endIdx) {
        openConnection();
        List<Report> listOfReports = ReportQueryHandler.getListOfReports(startIdx, endIdx);
        closeConnection();
        return listOfReports;
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
*/
    public static void formConnection_ID() {
        //FIRST CHECKING IN USER TABLE [USER_ID ALREADY EXISTS]
        System.out.println("----------------- Inside DBHandler.formConnection_ID() ---------------------- ");
        Network.global_ip_static = Network.getGlobalIP();

//        String sql_userCheck = "SELECT * FROM CONNECTION WHERE CONNECTION.user_id = " + quote(User.userID);
//        String sql_networkCheck = "SELECT * FROM CONNECTION WHERE CONNECTION.global_ip = " + quote(Network.getGlobalIP());
        String sql_conn_check = "SELECT connection_id FROM CONNECTION WHERE user_id = " + User.userID + " AND global_ip = " + quote(Network.global_ip_static);

        System.out.println(sql_conn_check);

        boolean if_exists_in_connection = false;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql_conn_check);

            // loop through the result set  
            if (rs.next() == true) {
                Network.connection_id_static = rs.getInt(1);
                System.out.println("rs.next == true in connection check table");
                if_exists_in_connection = true;
            }

            if (if_exists_in_connection == false) {
                createNewConnectionID(stmt);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createNewConnectionID(Statement stmt) {
        System.out.println("createNewConnectionID() is called .... ");
        boolean exists_in_network = false, exists_in_user = false;

        String sql_network_check = "SELECT * FROM NETWORK WHERE global_ip = " + quote(Network.global_ip_static);
        String sql_user_check = "SELECT * FROM USER WHERE user_id = " + User.userID;
        System.out.println("SQL USER CHECK IS <" + sql_user_check + ">");
        System.out.println("SQL NET CHECK IS <" + sql_network_check + ">");
        boolean if_exists_in_connection = false;

        try {
//            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql_network_check);

            // loop through the result set  
            if (rs.next() == true) {
                System.out.println("NETWORK CHECK ... true");
                exists_in_network = true;
            }

            rs = stmt.executeQuery(sql_user_check);

            if (rs.next() == true) {
                System.out.println("USER CHECK ... true");
                exists_in_user = true;
            }

            System.out.println("After two results ... exists_in_net = " + exists_in_network + " , exists_in_user = " + exists_in_user);
            
            if (!exists_in_user) {
                createNewUser(stmt);
            }
            if (!exists_in_network) {
                createNewNetwork(stmt); 
            }
            createNewConnection(stmt);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createNewUser(Statement stmt) {
        //get the count
//        openConnection();
        System.out.println("Inside createNewUser() ... ");
        String sql = "SELECT COUNT(*) FROM USER";
        try {
//            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int cnt = 0;
            if (rs.next()) {
                cnt = rs.getInt(1);
            }
//            cnt++;
//            User.userID = cnt; //assign user id

            //Now insert ...
            //INSERT INTO USER VALUES (2, 'mahim_mahbub', 'mahim1997mahbub@gmail.com', '1234')
            String sql_inserter = "INSERT INTO USER VALUES (" + User.userID + "," + quote(User.userName) + "," + quote(User.userEmailAddress) + "," + quote(User.userPassword) + ")";
            System.out.println("New user creation <" + sql_inserter + ">");
            ResultSet executeQuery = stmt.executeQuery(sql_inserter);

            System.out.println("--->>Created new user ... ");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        closeConnection();
    }

    private static void createNewNetwork(Statement stmt) {
//        openConnection();
        //Create new network

        String sql = "INSERT INTO NETWORK VALUES (" + quote(Network.global_ip_static) + "," + quote(Network.asn_static) + "," + quote(Network.city_static) + ","
                + quote(Network.org_static) + "," + quote(Network.carrier_static) + "," + quote(Network.latitude_static) + "," + quote(Network.longitude_static) + ","
                + quote(Network.country_static) + "," + quote(Network.region_static) + "," + quote(Network.postal_static)
                + ")";
        System.out.println("New neetwork creator <"  + sql + ">");
        try {
//            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("--->>   Created new network !! ... ");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        closeConnection();
    }

    private static void createNewConnection(Statement stmt) {
//        openConnection();
        //get the count
        String sql = "SELECT COUNT(*) FROM CONNECTION";
        try {
//            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int cnt = 0;
            if (rs.next()) {
                cnt = rs.getInt(1);
            }
            cnt++;
            Network.connection_id_static = cnt; //assign user id
            Network.global_ip_static = Network.getGlobalIP();
            //Create new connection
            //INSERT INTO USER VALUES (2, 'mahim_mahbub', 'mahim1997mahbub@gmail.com', '1234')
            String sql_inserter = "INSERT INTO CONNECTION VALUES (" + Network.connection_id_static + "," + quote(Network.global_ip_static) + "," + User.userID + ")";

            ResultSet executeQuery = stmt.executeQuery(sql_inserter);

            System.out.println("--->>  Created new connection ... ");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        closeConnection();
    }
}
